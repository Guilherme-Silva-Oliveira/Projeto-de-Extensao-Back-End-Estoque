package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;

import java.util.List;
import java.util.Optional;

public interface TipoFornecedorPort {

    TipoFornecedor save(TipoFornecedor tipoFornecedor);

    List<TipoFornecedor> findAll();

    Optional<TipoFornecedor> findById(Integer id);

    void delete(TipoFornecedor tipoFornecedor);
}
