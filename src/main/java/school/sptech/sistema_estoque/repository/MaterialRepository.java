package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.sistema_estoque.model.estoque.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
