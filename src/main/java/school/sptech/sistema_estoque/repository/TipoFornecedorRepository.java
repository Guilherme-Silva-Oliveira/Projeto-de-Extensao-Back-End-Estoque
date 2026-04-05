package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;

public interface TipoFornecedorRepository extends JpaRepository<TipoFornecedor, Integer> {
}
