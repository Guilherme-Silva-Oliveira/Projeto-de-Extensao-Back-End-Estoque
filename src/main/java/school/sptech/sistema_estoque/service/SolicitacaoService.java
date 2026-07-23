package school.sptech.sistema_estoque.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.front.FrontResponse;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.mapper.AlertaMapper;
import school.sptech.sistema_estoque.dto.mapper.HistoricoMapper;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.enums.StatusAlertaSolicitacao;
import school.sptech.sistema_estoque.enums.StatusSolicitacao;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SolicitacaoService {
    private final ProfessorPort professorPort;
    private final SolicitacaoPort solicitacaoPort;
    private final MotivoPort motivoPort;
    private final MaterialPort materialPort;

    public Solicitacao cadastrarSolicitacao(SolicitacaoRequest request) {
        if (request == null){throw new EntidadeInvalidException("Solicitacao Inválida");}
        Professor professor = professorPort.findById(request.idProfessor()).orElseThrow(()-> new EntidadeNaoExisteException("Professor Não Encontrado"));
        Motivo motivo = motivoPort.findById(request.idMotivo()).orElseThrow(()-> new EntidadeNaoExisteException("Motivo Não Encontrado"));

        List<String> listaMateriais = Arrays.asList(request.materiais().split(","));
        List<String> listaQuantidades = Arrays.asList(request.quantidade().split(","));
        List<String> listaDevolucoes = Arrays.asList(request.deveDevolver().split(","));

        Solicitacao solicitacao = SolicitacaoMapper.toEntity(request, professor,motivo, request.dataSolicitacao(), StatusSolicitacao.RECEBIDA);
        Solicitacao paraSalvarHistorico = solicitacaoPort.save(solicitacao);

        List<String> materiaisFaltando = new ArrayList<>();
        List<Integer> quantidadesFaltando = new ArrayList<>();
        Boolean existeFaltando = false;
        for (int i = 0; i < listaMateriais.size(); i++) {
            Material material = materialPort.findByNomeMaterial(listaMateriais.get(i)).orElseThrow(()-> new EntidadeNaoExisteException("Material Não Encontrado"));
            ListaMaterial listaMaterial = getNovaListaMaterial(paraSalvarHistorico, Integer.valueOf(listaQuantidades.get(i)), Boolean.valueOf(listaDevolucoes.get(i)), material);
            solicitacaoPort.salvarLista(listaMaterial);
            if (material.getQuantidade() < listaMaterial.getQuantidade()) {
                Integer faltantes = listaMaterial.getQuantidade() - material.getQuantidade();
                solicitacao.setAlerta(String.format(
                        "%s: %s %d faltando",
                        StatusAlertaSolicitacao.MATERIAIS_INSUFICIENTES.getDescricao(),
                        material.getNomeMaterial(),
                        faltantes
                ));
                materiaisFaltando.add(material.getNomeMaterial());
                quantidadesFaltando.add(faltantes);
                existeFaltando = true;
            } else if (material.getQuantidade() == listaMaterial.getQuantidade()) {
                if (!existeFaltando) {
                    solicitacao.setAlerta(String.format(
                            "%s: Após a solicitação, o estoque ficará sem itens",
                            StatusAlertaSolicitacao.ESTOQUE_VAZIO.getDescricao()
                    ));
                }
            }else {
                if (!existeFaltando){
                    solicitacao.setAlerta(String.format(
                            "%s: Material encaminhado para solicitação",
                            StatusAlertaSolicitacao.TUDO_CERTO.getDescricao()
                    ));
                }
            }
            if (materiaisFaltando.size() > 1){
                List<String> itens = new ArrayList<>();
                for (int j = 0; j < materiaisFaltando.size(); j++) {
                    itens.add(quantidadesFaltando.get(j) + " " + materiaisFaltando.get(j));
                }
                String alerta = String.join(", ", itens);
                solicitacao.setAlerta(String.format(
                        "%s: %s faltando",
                        StatusAlertaSolicitacao.MATERIAIS_INSUFICIENTES.getDescricao(),
                        alerta
                ));
            }
        }
        solicitacaoPort.saveHistorico(HistoricoMapper.toEntity(paraSalvarHistorico));
        solicitacaoPort.save(paraSalvarHistorico);
        return paraSalvarHistorico;
    }

    public List<Solicitacao> listarSolicitacoes() {
        return solicitacaoPort.findAll();
    }

    public void excluirSolicitacao(Integer id){
        Solicitacao solicitacao = solicitacaoPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Solicitação Não Encontrado"));
        solicitacaoPort.delete(solicitacao);
    }

    @Transactional
    public Solicitacao avaliar(Integer id, Boolean novaDecisao) {
        Solicitacao solicitacao = solicitacaoPort.findById(id).orElseThrow(() -> new EntidadeInvalidException("Solicitação não encontrada"));
        List<Optional<Historico>> historicos = solicitacaoPort.findBySolicitacaoId(id);
        List<Optional<ListaMaterial>> listaMaterial = solicitacaoPort.findListaBySolicitacaoId(solicitacao.getId());
        if (listaMaterial.isEmpty()){throw new EntidadeInvalidException("Nenhum Material Associado à esta Solicitação!!");}
        StatusSolicitacao status = null;
        if (novaDecisao){
            status = StatusSolicitacao.ACEITA;
            if (solicitacao.getAlerta().contains(StatusAlertaSolicitacao.MATERIAIS_INSUFICIENTES.getDescricao())){
                solicitacaoPort.salvarAlertaSolicitacao(getAlertaSolicitacao(solicitacao, solicitacao.getAlerta()));
                status = StatusSolicitacao.PENDENTE_COMPRA;
                atualizarQuantidadeMateriais(listaMaterial);
            }else if (solicitacao.getAlerta().contains(StatusAlertaSolicitacao.ESTOQUE_VAZIO.getDescricao())){
                solicitacaoPort.salvarAlertaSolicitacao(getAlertaSolicitacao(solicitacao, solicitacao.getAlerta()));
                atualizarQuantidadeMateriais(listaMaterial);
            }
        }else {status = StatusSolicitacao.REJEITADA;}
        if (!historicos.isEmpty()){
            Historico historico = historicos.getFirst().orElseThrow(() -> new EntidadeInvalidException("Histórico não encontrado"));
            solicitacaoPort.saveHistorico(getNovoHistorico(historico.getSolicitacao(), status));
        }else{
            throw new EntidadeInvalidException("Nenhum Histórico Associado à esta Solicitação!!");
        }
        return solicitacao;
    }

    public Historico getNovoHistorico(Solicitacao solicitacao, StatusSolicitacao status){
        Historico h = new Historico();
        h.setSolicitacao(solicitacao);
        h.setDataAlteracao(LocalDateTime.now());
        h.setStatusSolicitacao(status.getDescricao());
        return h;
    }

    public void atualizarStatus(Integer solicitacaoId, Integer status) {
        Solicitacao solicitacao = solicitacaoPort.findById(solicitacaoId).orElseThrow(() -> new EntidadeInvalidException("Solicitação não encontrada"));
        Status statusBD = solicitacaoPort.findStatusById(status).orElseThrow(() -> new EntidadeInvalidException("Status não encontrado"));
        StatusSolicitacao statusTarget = StatusSolicitacao.valueOf(statusBD.getDescStatus());
        solicitacaoPort.saveHistorico(getNovoHistorico(solicitacao, statusTarget));
    }

    @Transactional
    public void finalizarSolicitacao(Integer solicitacaoId){
        Solicitacao solicitacao = solicitacaoPort.findById(solicitacaoId).orElseThrow(() -> new EntidadeInvalidException("Solicitação não encontrada"));
        List<Optional<Historico>> historicos = solicitacaoPort.findBySolicitacaoId(solicitacaoId);
        if (!historicos.isEmpty()){
            Historico historico = historicos.getFirst().orElseThrow(() -> new EntidadeInvalidException("Histórico não encontrado"));
            solicitacaoPort.saveHistorico(getNovoHistorico(historico.getSolicitacao(), getNextStatusParaFinalizar(solicitacao)));
        }else{
            throw new EntidadeInvalidException("Nenhum Histórico Associado à esta Solicitação!!");
        }
    }

    @Transactional
    public void verificarPrazos(){
        Status statusExpirado = solicitacaoPort.findStatusById(5).orElseThrow(() -> new EntidadeInvalidException("Status não encontrado"));
        List<Solicitacao> solicitacoes = solicitacaoPort.findAll();
        solicitacoes.forEach(solicitacao -> {
            if (solicitacao.getDataParaEnvio().isBefore(LocalDateTime.now())) {
                solicitacaoPort.saveHistorico(getNovoHistorico(solicitacao,StatusSolicitacao.valueOf(statusExpirado.getDescStatus())));
            }
        });
    }

    public void devolverMaterial(Integer solicitacaoId){
        Solicitacao solicitacao = solicitacaoPort.findById(solicitacaoId).orElseThrow(() -> new EntidadeInvalidException("Solicitação não encontrada"));
        List<Optional<AlertaDevolucao>> alertasOpt = solicitacaoPort.findAlertaBySolicitacaoId(solicitacao.getId());
        if (!alertasOpt.isEmpty()) {
            List<AlertaDevolucao> alertas = alertasOpt.stream().filter(Optional::isPresent).map(Optional::get).toList();
            for (AlertaDevolucao a : alertas){
                if (!a.getDevolvido()){
                    a.setDevolvido(true);
                    solicitacaoPort.salvarAlerta(a);
                    solicitacaoPort.saveHistorico(getNovoHistorico(solicitacao,StatusSolicitacao.FINALIZADA));
                }
            }
        }else {
            throw new EntidadeInvalidException("Nenhum Alerta de Devolução Associado à esta Solicitação!!");
        }
    }

    public FrontResponse gerarRelatorio(Integer professorId){
        Professor professor = professorPort.findById(professorId).orElseThrow(() -> new EntidadeInvalidException("Professor não encontrado"));
        Solicitacao solicitacao = solicitacaoPort.findByProfessorId(professor.getId()).orElseThrow(() -> new EntidadeInvalidException("Solicitação não encontrada"));
        List<Optional<ListaMaterial>> listaMaterial = solicitacaoPort.findListaBySolicitacaoId(solicitacao.getId());
        return getNovoFrontResponse(solicitacao, professor, listaMaterial);
    }

    public AlertaSolicitacao getAlertaSolicitacao(Solicitacao solicitacao, String alerta){
        AlertaSolicitacao a = new AlertaSolicitacao();
        a.setSolicitacao(solicitacao);
        a.setResolvido(false);
        a.setDescricao(alerta);
        return a;
    }

    public void atualizarQuantidadeMateriais(List<Optional<ListaMaterial>> listaMaterial){
        for (Optional<ListaMaterial> listaMaterialOpt : listaMaterial) {
            if (listaMaterialOpt.isPresent()) {
                Material material = listaMaterialOpt.get().getMaterial();
                material.setQuantidade(material.getQuantidade() - listaMaterialOpt.get().getQuantidade());
                materialPort.save(material);
            }
        }
    }

    public StatusSolicitacao getNextStatusParaFinalizar(Solicitacao solicitacao){
        List<Optional<ListaMaterial>> listaMaterial = solicitacaoPort.findListaBySolicitacaoId(solicitacao.getId());
        StatusSolicitacao status = null;
        if (listaMaterial.isEmpty()) {
            throw new EntidadeInvalidException("Nenhuma Lista de Materiais Associada à esta Solicitação!!");
        }else{
            for (Optional<ListaMaterial> lmOpt : listaMaterial) {
                if (lmOpt.isPresent()) {
                    ListaMaterial lm = lmOpt.get();
                    if (lm.getDeveDevolver()){
                        status = StatusSolicitacao.PENDENTE_DEVOLUCAO;
                    }
                }
            }
        }
        if (status != null){
            solicitacaoPort.salvarAlerta(AlertaMapper.toEntity(solicitacao));
        }
        return status;
    }

    public ListaMaterial getNovaListaMaterial(Solicitacao solicitacao, Integer quantidade,Boolean deveDevolver, Material material){
        ListaMaterial l = new ListaMaterial();
        l.setSolicitacao(solicitacao);
        l.setQuantidade(quantidade);
        l.setReservado(false);
        l.setMaterial(material);
        l.setDeveDevolver(deveDevolver);
        return l;
    }

    public FrontResponse getNovoFrontResponse(Solicitacao solicitacao, Professor professor, List<Optional<ListaMaterial>> listaMateriais){
        List<String> listaMateriaisSave = new ArrayList<>();
        List<String> alertas = new ArrayList<>();
        if (listaMateriais.isEmpty()){
            throw new EntidadeInvalidException("Nenhuma Lista de Materiais Associada à esta Solicitação!!");
        }else {
            for (Optional<ListaMaterial> lmOpt : listaMateriais) {
                lmOpt.ifPresent(lm -> listaMateriaisSave.add(lm.getMaterial().getNomeMaterial()));
            }
        }
        List<Optional<AlertaDevolucao>> alertasOpt = solicitacaoPort.findAlertaBySolicitacaoId(solicitacao.getId());
        alertasOpt.forEach(opt -> opt.ifPresent(alerta -> alertas.add(alerta.getDescricao())));
        return new FrontResponse(solicitacao.getDescricao(),solicitacao.getDataSolicitacao(),solicitacao.getDataParaEnvio(),professor.getNome(),listaMateriaisSave,alertas);
    }
}
