package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.port.UnidadeMedidaPort;
import school.sptech.sistema_estoque.repository.UnidadeMedidaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UnidadeMedidaAdapter implements UnidadeMedidaPort {
    private final UnidadeMedidaRepository unidadeMedidaRepository;

    public UnidadeMedidaAdapter(UnidadeMedidaRepository unidadeMedidaRepository) {
        this.unidadeMedidaRepository = unidadeMedidaRepository;
    }

    @Override
    public UnidadeMedida save(UnidadeMedida unidadeMedida) {
        return unidadeMedidaRepository.save(unidadeMedida);
    }

    @Override
    public Optional<UnidadeMedida> findById(Integer id) {
        return unidadeMedidaRepository.findById(id);
    }

    @Override
    public List<UnidadeMedida> findAll() {
        return unidadeMedidaRepository.findAll();
    }

    @Override
    public void delete(UnidadeMedida unidadeMedida) {
        unidadeMedidaRepository.delete(unidadeMedida);
    }
}
