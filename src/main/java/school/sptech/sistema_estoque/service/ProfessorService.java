package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.ProfessorRequest;
import school.sptech.sistema_estoque.dto.estoque.ProfessorResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.InvalidProfessorRequestException;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.repository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository repository;
    private final SistemaMapper mapper;
    public ProfessorService(ProfessorRepository repository, SistemaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProfessorResponse cadastrarProfessor(ProfessorRequest request){
        if (request == null){
            throw new InvalidProfessorRequestException("Professor Inválido");
        }
        Professor professor = mapper.toProfessorEntity(request);
        repository.save(professor);
        return mapper.toProfessorResponse(professor);
    }

    public List<ProfessorResponse> listarProfessor(){
        return repository.findAll().stream()
                .map(mapper::toProfessorResponse)
                .toList();
    }

    public void excluirProfessor(Integer id){
        Optional<Professor> opt = repository.findById(id);
        if (opt.isEmpty()){
            throw new InvalidProfessorRequestException("Professor Não Encontrada");
        }
        repository.delete(opt.get());
    }
}
