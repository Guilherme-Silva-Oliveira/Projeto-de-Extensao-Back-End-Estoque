package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.dto.estoque.dashboard.MaterialMaisSolicitadoDto;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Material;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MaterialPort {
    Material save(Material material);
    List<Material> findAll();
    Boolean existsByNomeMaterialAndAlmoxarifadoId(String nomeMaterial, Integer idAlmoxarifado);
    Optional<Material> findById(Integer id);
    Optional<Material> findByNomeMaterial(String nome);
    void delete(Material material);
    List<MaterialMaisSolicitadoDto> findMaterialMaisSolicitadoPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim);
}

