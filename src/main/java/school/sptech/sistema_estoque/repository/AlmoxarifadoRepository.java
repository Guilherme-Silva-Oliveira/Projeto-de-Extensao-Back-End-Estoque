package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;

import java.util.Optional;

public interface AlmoxarifadoRepository extends JpaRepository<Almoxarifado, Integer> {

    Optional<Almoxarifado> findByNumeroSala(Integer numeroSala);

}
