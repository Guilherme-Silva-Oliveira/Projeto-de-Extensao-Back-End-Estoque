package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.sistema_estoque.model.estoque.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {}
