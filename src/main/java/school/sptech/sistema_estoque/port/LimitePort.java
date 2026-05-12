package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Limite;

import java.util.List;
import java.util.Optional;

public interface LimitePort {

    Limite save(Limite limite);

    List<Limite> findAll();

    Optional<Limite> findById(Integer id);

    void delete(Limite limite);
}
