package school.sptech.sistema_estoque.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Motivo;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.observer.MovimentacaoObserver;
import school.sptech.sistema_estoque.port.EscalaPort;
import school.sptech.sistema_estoque.port.MotivoPort;
import school.sptech.sistema_estoque.port.ProfessorPort;
import school.sptech.sistema_estoque.port.SolicitacaoPort;
import school.sptech.sistema_estoque.repository.EscalaRepository;
import school.sptech.sistema_estoque.repository.ProfessorRepository;
import school.sptech.sistema_estoque.repository.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {
    private final ProfessorPort professorPort;
    private final EscalaPort escalaPort;
    private final SolicitacaoPort solicitacaoPort;
    private final MotivoPort motivoPort;

    public SolicitacaoService(ProfessorPort professorPort, EscalaPort escalaPort, SolicitacaoPort solicitacaoPort, MotivoPort motivoPort) {
        this.professorPort = professorPort;
        this.escalaPort = escalaPort;
        this.solicitacaoPort = solicitacaoPort;
        this.motivoPort = motivoPort;
    }

    public Solicitacao cadastrarSolicitacao(SolicitacaoRequest request) {
        if (request == null){throw new EntidadeInvalidException("Solicitacao Inválida");}
        Optional<Professor> professorOptional = professorPort.findById(request.idProfessor());
        if (professorOptional.isEmpty()){throw new EntidadeInvalidException("Professor não encontrado");}
        Optional<Motivo> motivoOptional = motivoPort.findById(request.idMotivo());
        if (motivoOptional.isEmpty()){throw new EntidadeInvalidException("Motivo não encontrado");}
        Solicitacao solicitacao = SolicitacaoMapper.toEntity(request, professorOptional.get(),motivoOptional.get(), request.dataSolicitacao());
        return solicitacaoPort.save(solicitacao);
    }

    public List<Solicitacao> listarSolicitacoes() {
        return solicitacaoPort.findAll();
    }

    public void excluirSolicitacao(Integer id){
        Optional<Solicitacao> opt = solicitacaoPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Solicitacao Não Encontrada");}
        solicitacaoPort.delete(opt.get());
    }

    @Transactional
    public Solicitacao avaliar(Integer id, Boolean novaDecisao) {
    Solicitacao solicitacao = solicitacaoPort.findById(id)
            .orElseThrow(() -> new EntidadeInvalidException("Solicitação não encontrada"));

    solicitacao.setAceito(novaDecisao);

    Solicitacao saved = solicitacaoPort.save(solicitacao);

    return saved;
}

    public Page<Solicitacao> listarSolicitacoesBoolean(Boolean bool, Pageable pageable) {
        return solicitacaoPort.findByIsAceito(bool, pageable);
    }
}
