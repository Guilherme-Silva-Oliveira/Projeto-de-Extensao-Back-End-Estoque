package school.sptech.sistema_estoque.adapter;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.dto.estoque.dashboard.MaterialMaisSolicitadoDto;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.repository.ListaMaterialRepository;
import school.sptech.sistema_estoque.repository.MaterialRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class MaterialAdapter implements MaterialPort {

    private final MaterialRepository materialRepository;
    private final ListaMaterialRepository listaMaterialRepository;

    public MaterialAdapter(MaterialRepository materialRepository, ListaMaterialRepository listaMaterialRepository) {
        this.materialRepository = materialRepository;
        this.listaMaterialRepository = listaMaterialRepository;
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
    public List<MaterialMaisSolicitadoDto> findMaterialMaisSolicitadoPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return listaMaterialRepository.findMaterialMaisSolicitadoPorPeriodo(dataInicio, dataFim, PageRequest.of(0, 1));
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Optional<Material> findByNomeMaterial(String nome) {
        return materialRepository.findByNomeMaterial(nome);
    }
}