
package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Almoxarife;

import java.util.List;
import java.util.Optional;

public interface AlmoxarifePort {
    Almoxarife save(Almoxarife almoxarife);
    List<Almoxarife> findAll();
    Optional<Almoxarife> findByEmail(String email);
    Optional<Almoxarife> findById(Integer id);
    void delete(Almoxarife almoxarife);
    boolean existsByEmailAndAlmoxarifadoId(String email, Integer almoxarifadoId);

}

