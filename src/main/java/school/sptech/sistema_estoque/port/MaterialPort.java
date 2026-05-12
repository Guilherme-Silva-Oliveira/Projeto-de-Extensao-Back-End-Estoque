package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Material;

import java.util.Optional;

public interface MaterialPort {

    Optional<Material> findById(Integer id);
}

