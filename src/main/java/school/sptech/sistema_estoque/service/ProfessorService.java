package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorPatchDto;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.port.ProfessorPort;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessorService {
    private final ProfessorPort professorPort;

    public Professor cadastrarProfessor(ProfessorRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Professor Inválido");}
        if (professorPort.existsByEmail(request.email())) {throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um professor cadastrado com esse email");}
        if (professorPort.existsByTelefone(request.telefone())) {throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um professor cadastrado com esse telefone");}
        Professor professor = new Professor(null, request.nome(), request.email(), request.telefone());
        return professorPort.save(professor);
    }

    public List<Professor> listarProfessor() {
        return professorPort.findAll();
    }

    public void excluirProfessor(Integer id){
        Professor professor = professorPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Professor Não Encontrado"));
        professorPort.delete(professor);
    }

    public Professor atualizarProfessor(Integer id, ProfessorPatchDto request){
        Professor professor = professorPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Professor Não Encontrado"));
        if(request.nome() != null){professor.setNome(request.nome());}
        if(request.email() != null){professor.setEmail(request.email());}
        if(request.telefone() != null){professor.setTelefone(request.telefone());}
        return professorPort.save(professor);
    }
}
