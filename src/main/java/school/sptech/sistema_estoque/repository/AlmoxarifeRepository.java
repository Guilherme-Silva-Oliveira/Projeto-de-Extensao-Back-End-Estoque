package school.sptech.sistema_estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;

public interface AlmoxarifeRepository extends JpaRepository<Almoxarife, Integer> {

    Optional<Almoxarife> findByEmail(String email);
}
