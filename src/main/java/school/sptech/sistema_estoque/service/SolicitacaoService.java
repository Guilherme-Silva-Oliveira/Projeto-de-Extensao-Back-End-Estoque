package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.exception.InvalidProfessorRequestException;
import school.sptech.sistema_estoque.exception.InvalidSolicitacaoRequestException;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.repository.ProfessorRepository;
import school.sptech.sistema_estoque.repository.SolicitacaoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {
    private final ProfessorRepository prorepository;
    private final SolicitacaoRepository solrepository;
    public SolicitacaoService(ProfessorRepository prorepository, SolicitacaoRepository solrepository) {
        this.prorepository = prorepository;
        this.solrepository = solrepository;
    }

    public List<Solicitacao> listarSolicitacao() {
        return solrepository.findAll();
    }

    public Solicitacao cadastrarSolicitacao(SolicitacaoIARequest request) {
        if (request == null){throw new InvalidSolicitacaoRequestException("Solicitacao Inválida");}

        Optional<Professor> professorOptional = prorepository.findByNome(request.nome_professor());
        if (professorOptional.isEmpty()){throw new InvalidProfessorRequestException("Professor não encontrado");}

        String data = request.data_solicitacao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataFormatada = LocalDateTime.parse(data,formatter);
        Solicitacao solicitacao = new Solicitacao(null, professorOptional.get(), "", request.nome_material(), dataFormatada);
        return solrepository.save(solicitacao);
    }
}
