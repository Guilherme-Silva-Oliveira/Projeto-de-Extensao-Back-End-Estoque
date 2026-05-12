package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Material;

import java.util.List;
import java.util.Optional;

public interface MaterialPort {

    Material save(Material material);

    List<Material> findAll();

    Boolean existsByNomeMaterialAndAlmoxarifadoId(String nomeMaterial, Integer idAlmoxarifado);

    Optional<Material> findById(Integer id);

    void delete(Material material);
}

