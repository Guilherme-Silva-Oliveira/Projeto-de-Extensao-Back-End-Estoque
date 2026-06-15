package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorPort {
    Professor save(Professor professor);
    List<Professor> findAll();
    Optional<Professor> findById(Integer id);
    void delete(Professor professor);
    Boolean existsByEmailAndTelefone(String email, String telefone);
    Boolean existsByEmail(String email);
    Boolean existsByTelefone(String telefone);

}

