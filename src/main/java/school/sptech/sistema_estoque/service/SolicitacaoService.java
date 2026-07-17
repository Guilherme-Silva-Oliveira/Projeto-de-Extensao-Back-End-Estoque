package school.sptech.sistema_estoque.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.mapper.HistoricoMapper;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.enums.StatusSolicitacao;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.*;

import java.time.LocalDateTime;
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
        Material material = materialPort.findById(request.idMaterial()).orElseThrow(()-> new EntidadeNaoExisteException("Material Não Encontrado"));
        Solicitacao solicitacao = SolicitacaoMapper.toEntity(request, professor,motivo, request.dataSolicitacao(),material, StatusSolicitacao.RECEBIDA);
        Solicitacao paraSalvarHistorico = solicitacaoPort.save(solicitacao);
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
        List<Optional<Historico>> historicos = solicitacaoPort.findHistoricoById(id);
        StatusSolicitacao status = null;
        if (novaDecisao){status = StatusSolicitacao.ACEITA;
        }else {status = StatusSolicitacao.REJEITADA;}

        if (!historicos.isEmpty()){
            Historico historico = historicos.getFirst().orElseThrow(() -> new EntidadeInvalidException("Histórico não encontrado"));
            solicitacaoPort.saveHistorico(getNovoHistorico(historico.getSolicitacao(), status));
        }else{
            throw new EntidadeInvalidException("Nenhum Histórico Associado à esta Solicitação!!");
        }
        return solicitacaoPort.save(solicitacao);
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
    public void verificarPrazos(){
        Status statusExpirado = solicitacaoPort.findStatusById(5).orElseThrow(() -> new EntidadeInvalidException("Status não encontrado"));
        List<Solicitacao> solicitacoes = solicitacaoPort.findAll();
        solicitacoes.forEach(solicitacao -> {
            if (solicitacao.getDataParaEnvio().isBefore(LocalDateTime.now())) {
                solicitacaoPort.saveHistorico(getNovoHistorico(solicitacao,StatusSolicitacao.valueOf(statusExpirado.getDescStatus())));
            }
        });
    }
}
