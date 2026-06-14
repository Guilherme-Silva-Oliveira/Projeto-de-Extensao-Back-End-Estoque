package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Motivo;

import java.util.List;
import java.util.Optional;

public interface MotivoPort {
    Motivo save(Motivo motivo);
    List<Motivo> findAll();
    Optional<Motivo> findById(Integer id);
    void delete(Motivo motivo);
}
