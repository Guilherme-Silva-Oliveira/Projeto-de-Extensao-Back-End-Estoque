
package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Escala;

import java.util.Optional;

public interface EscalaRepository extends JpaRepository<Escala, Integer> {
    Optional<Escala> findByNomeEscala(String nomeEscala);
}

