package school.sptech.sistema_estoque.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.mapper.AlertaMapper;
import school.sptech.sistema_estoque.dto.mapper.HistoricoMapper;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.enums.StatusSolicitacao;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.*;

import java.time.LocalDateTime;
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
        Solicitacao solicitacao = SolicitacaoMapper.toEntity(request, professor,motivo, request.dataSolicitacao(), StatusSolicitacao.RECEBIDA);
        Solicitacao paraSalvarHistorico = solicitacaoPort.save(solicitacao);

        for (int i = 0; i < listaMateriais.size(); i++) {
            Material material = materialPort.findByNomeMaterial(listaMateriais.get(i)).orElseThrow(()-> new EntidadeNaoExisteException("Material Não Encontrado"));
            solicitacaoPort.salvarLista(getNovaListaMaterial(paraSalvarHistorico, Integer.valueOf(listaQuantidades.get(i)), material));
        }

        solicitacaoPort.saveHistorico(HistoricoMapper.toEntity(paraSalvarHistorico));
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
        StatusSolicitacao status = null;
        if (novaDecisao){status = StatusSolicitacao.ACEITA;
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

    public StatusSolicitacao getNextStatusParaFinalizar(Solicitacao solicitacao){
        StatusSolicitacao status = solicitacao.getDeveDevolver() ? StatusSolicitacao.PENDENTE_DEVOLUCAO : StatusSolicitacao.FINALIZADA;
        if (status.equals(StatusSolicitacao.PENDENTE_DEVOLUCAO)) {
            solicitacaoPort.salvarAlerta(AlertaMapper.toEntity(solicitacao));
        }
        return status;
    }

    public ListaMaterial getNovaListaMaterial(Solicitacao solicitacao, Integer quantidade, Material material){
        ListaMaterial l = new ListaMaterial();
        l.setSolicitacao(solicitacao);
        l.setQuantidade(quantidade);
        l.setReservado(false);
        l.setMaterial(material);
        return l;
    }
}
