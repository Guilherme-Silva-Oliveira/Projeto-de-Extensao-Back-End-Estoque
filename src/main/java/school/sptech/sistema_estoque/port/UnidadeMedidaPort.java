package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;

import java.util.List;
import java.util.Optional;

public interface UnidadeMedidaPort {

    UnidadeMedida save(UnidadeMedida unidadeMedida);

    Optional<UnidadeMedida > findById(Integer id);

    List<UnidadeMedida> findAll();

    void delete(UnidadeMedida unidadeMedida);
}
