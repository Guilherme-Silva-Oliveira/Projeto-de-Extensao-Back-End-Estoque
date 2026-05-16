package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.repository.MaterialRepository;

import java.util.List;
import java.util.Optional;

@Component
public class MaterialAdapter implements MaterialPort {

    private final MaterialRepository materialRepository;

    public MaterialAdapter(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Optional<Material> findById(Integer id) {
        return materialRepository.findById(id);
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Boolean existsByNomeMaterialAndAlmoxarifadoId(String nomeMaterial, Integer idAlmoxarifado) {
        return materialRepository.existsByNomeMaterialAndAlmoxarifadoId(nomeMaterial,idAlmoxarifado);
    }

    @Override
    public void delete(Material material) {
        materialRepository.delete(material);
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }
}

