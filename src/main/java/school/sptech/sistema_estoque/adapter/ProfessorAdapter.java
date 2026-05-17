package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.port.ProfessorPort;
import school.sptech.sistema_estoque.repository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProfessorAdapter implements ProfessorPort {

    private final ProfessorRepository professorRepository;

    public ProfessorAdapter(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Optional<Professor> findById(Integer id) {
        return professorRepository.findById(id);
    }

    @Override
    public void delete(Professor professor) {
        professorRepository.delete(professor);
    }

    @Override
    public Boolean existsByEmailAndTelefone(String email, String telefone) {
        return professorRepository.existsByEmailAndTelefone(email, telefone);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return professorRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByTelefone(String telefone) {
        return professorRepository.existsByTelefone(telefone);
    }

}

