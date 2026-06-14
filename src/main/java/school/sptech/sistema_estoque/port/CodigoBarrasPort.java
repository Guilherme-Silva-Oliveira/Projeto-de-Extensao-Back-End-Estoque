package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.CodigoBarras;

import java.util.Optional;

public interface CodigoBarrasPort {
    CodigoBarras save(CodigoBarras codigoBarras);
    Optional<CodigoBarras> findById(String id);
}
