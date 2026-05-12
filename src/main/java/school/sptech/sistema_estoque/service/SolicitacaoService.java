package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.port.EscalaPort;
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

    public SolicitacaoService(ProfessorPort professorPort, EscalaPort escalaPort, SolicitacaoPort solicitacaoPort) {
        this.professorPort = professorPort;
        this.escalaPort = escalaPort;
        this.solicitacaoPort = solicitacaoPort;
    }

    public Solicitacao cadastrarSolicitacao(SolicitacaoRequest request) {
        if (request == null){throw new EntidadeInvalidException("Solicitacao Inválida");}
        Optional<Professor> professorOptional = professorPort.findById(request.idProfessor());
        if (professorOptional.isEmpty()){throw new EntidadeInvalidException("Professor não encontrado");}
        Solicitacao solicitacao = SolicitacaoMapper.toEntity(request, professorOptional.get(), request.dataSolicitacao());
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
}
