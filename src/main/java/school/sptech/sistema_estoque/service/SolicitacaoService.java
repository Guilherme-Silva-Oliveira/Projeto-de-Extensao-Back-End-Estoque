package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.repository.EscalaRepository;
import school.sptech.sistema_estoque.repository.ProfessorRepository;
import school.sptech.sistema_estoque.repository.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {
    private final ProfessorRepository prorepository;
    private final EscalaRepository escalaRepository;
    private final SolicitacaoRepository solrepository;

    public SolicitacaoService(ProfessorRepository prorepository, EscalaRepository escalaRepository, SolicitacaoRepository solrepository) {
        this.prorepository = prorepository;
        this.escalaRepository = escalaRepository;
        this.solrepository = solrepository;
    }

    public Solicitacao cadastrarSolicitacao(SolicitacaoRequest request) {
        if (request == null){throw new EntidadeInvalidException("Solicitacao Inválida");}
        Optional<Professor> professorOptional = prorepository.findById(request.idProfessor());
        if (professorOptional.isEmpty()){throw new EntidadeInvalidException("Professor não encontrado");}
        Solicitacao solicitacao = SolicitacaoMapper.toEntity(request, professorOptional.get(), request.dataSolicitacao());
        return solrepository.save(solicitacao);
    }

    public List<Solicitacao> listarSolicitacoes() {
        return solrepository.findAll();
    }

    public void excluirSolicitacao(Integer id){
        Optional<Solicitacao> opt = solrepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Solicitacao Não Encontrada");}
        solrepository.delete(opt.get());
    }
}
