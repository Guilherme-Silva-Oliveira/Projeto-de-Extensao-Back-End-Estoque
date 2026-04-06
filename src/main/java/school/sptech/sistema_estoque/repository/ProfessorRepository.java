package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.sistema_estoque.model.estoque.Professor;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    public Optional<Professor> findByName(String nome);
}
