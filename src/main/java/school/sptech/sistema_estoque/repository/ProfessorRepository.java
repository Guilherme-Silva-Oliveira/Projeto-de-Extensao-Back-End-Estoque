package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.sistema_estoque.model.estoque.Professor;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    // Buscas gerais
    Optional<Professor> findByNome(String nome);
    Optional<Professor> findByEmail(String email);
    Optional<Professor> findByTelefone(String telefone);
    Optional<Professor> findByNomeAndEmail(String nome, String email);

    // Verificação de duplicata antes de cadastrar
    Boolean existsByEmail(String email);
    Boolean existsByTelefone(String telefone);
    Boolean existsByEmailAndTelefone(String email, String telefone);
}
