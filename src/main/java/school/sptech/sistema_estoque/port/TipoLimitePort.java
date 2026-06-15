package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.TipoLimite;

import java.util.List;
import java.util.Optional;

public interface TipoLimitePort {
    TipoLimite save(TipoLimite tipoLimite);
    List<TipoLimite> findAll();
    Optional<TipoLimite> findById(Integer id);
    void delete(TipoLimite tipoLimite);
}

