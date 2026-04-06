package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Escala;

public interface EscalaRepository extends JpaRepository<Escala, Integer> {
}
