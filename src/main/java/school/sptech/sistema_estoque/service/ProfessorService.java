package school.sptech.sistema_estoque.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorRequest;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.repository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository repository;
    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor cadastrarProfessor(ProfessorRequest request){
        if (request == null){throw new EntidadeInvalidException("Professor Inválido");}
        if (repository.existsByEmailAndTelefone(request.email(), request.telefone())){
            throw new EntidadeConflictException("Já existe um almoxarife cadastrado com esse email e id de almoxarifado");
        }
        Professor professor = new Professor(null, request.nome(), request.email(), request.telefone());
        return repository.save(professor);
    }

    public List<Professor> listarProfessor(){
        return repository.findAll();
    }

    public void excluirProfessor(Integer id){
        Optional<Professor> opt = repository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Professor Não Encontrada");}
        repository.delete(opt.get());
    }
}
