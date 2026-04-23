package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;

import java.util.Optional;

public interface AlmoxarifeRepository extends JpaRepository<Almoxarife, Integer> {

    // Buscas gerais
    Optional<Almoxarife> findByNome(String nome);
    Optional<Almoxarife> findByEmail(String email);
    Optional<Almoxarife> findByAlmoxarifadoId(Integer almoxarifadoId);
    Optional<Almoxarife> findByNomeAndEmail(String nome, String email);

    // Verificação de duplicata antes de cadastrar
    Boolean existsByEmail(String email);
    Boolean existsByAlmoxarifadoId(Integer almoxarifadoId);
    Boolean existsByEmailAndAlmoxarifadoId(String email, Integer almoxarifadoId);

}
