
package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Escala;

import java.util.List;
import java.util.Optional;

public interface EscalaPort {
    Escala save(Escala escala);
    List<Escala> findAll();
    Optional<Escala> findByNomeEscala(String nomeEscala);
    Optional<Escala> findById(Integer id);
    void delete(Escala escala);

}

