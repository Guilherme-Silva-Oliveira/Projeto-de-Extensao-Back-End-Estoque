package school.sptech.sistema_estoque.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorPatchDto;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorRequest;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.port.ProfessorPort;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorPort professorPort;
    public ProfessorService(ProfessorPort professorPort) {
        this.professorPort = professorPort;
    }

    public Professor cadastrarProfessor(ProfessorRequest request){
        if (request == null){throw new EntidadeInvalidException("Professor Inválido");}
        if (professorPort.existsByEmailAndTelefone(request.email(), request.telefone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um almoxarife cadastrado com esse email e id de almoxarifado");
        }
        Professor professor = new Professor(null, request.nome(), request.email(), request.telefone());
        return professorPort.save(professor);
    }

    public List<Professor> listarProfessor(){
        return professorPort.findAll();
    }

    public void excluirProfessor(Integer id){
        Optional<Professor> opt = professorPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Professor Não Encontrada");}
        professorPort.delete(opt.get());
    }

    public Professor atualizarProfessor(Integer id, ProfessorPatchDto request){

        Optional<Professor> opt = professorPort.findById(id);

        if(opt.isEmpty()){
            throw new EntidadeNaoExisteException("Professor Não Encontrado");
        }

        Professor professor = opt.get();

        if(request.nome() != null){
            professor.setNome(request.nome());
        }

        if(request.email() != null){
            professor.setEmail(request.email());
        }

        if(request.telefone() != null){
            professor.setTelefone(request.telefone());
        }

        return professorPort.save(professor);
    }
}
