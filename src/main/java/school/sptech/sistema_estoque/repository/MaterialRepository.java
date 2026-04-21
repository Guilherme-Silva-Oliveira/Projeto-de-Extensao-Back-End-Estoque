package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.sistema_estoque.model.estoque.Material;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    // Buscas gerais
    Optional<Material> findByNomeMaterial(String nomeMaterial);
    List<Material> findByCategoriaId(Integer categoriaId);
    List<Material> findByAlmoxarifadoId(Integer almoxarifadoId);
    List<Material> findByAlmoxarifadoIdAndCategoriaId(Integer almoxarifadoId, Integer categoriaId);

    // Verificação de duplicata antes de cadastrar
    Boolean existsByNomeMaterial(String nomeMaterial);
    Boolean existsByNomeMaterialAndAlmoxarifadoId(String nomeMaterial, Integer almoxarifadoId);

}
