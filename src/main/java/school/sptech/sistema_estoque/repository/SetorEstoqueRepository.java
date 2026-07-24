package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.SetorEstoque;

import java.util.List;
import java.util.Optional;

public interface SetorEstoqueRepository extends JpaRepository<SetorEstoque, Integer> {
    Optional<SetorEstoque> findByIdentificadorSetor(String identificador);
}
