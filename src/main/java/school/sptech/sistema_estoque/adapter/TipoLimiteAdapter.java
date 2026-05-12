package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.TipoLimite;
import school.sptech.sistema_estoque.port.TipoLimitePort;
import school.sptech.sistema_estoque.repository.TipoLimiteRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TipoLimiteAdapter implements TipoLimitePort {
    private final TipoLimiteRepository tipoLimiteRepository;

    public TipoLimiteAdapter(TipoLimiteRepository tipoLimiteRepository) {
        this.tipoLimiteRepository = tipoLimiteRepository;
    }

    @Override
    public TipoLimite save(TipoLimite tipoLimite) {
        return tipoLimiteRepository.save(tipoLimite);
    }

    @Override
    public List<TipoLimite> findAll() {
        return tipoLimiteRepository.findAll();
    }

    @Override
    public Optional<TipoLimite> findById(Integer id) {
        return tipoLimiteRepository.findById(id);
    }

    @Override
    public void delete(TipoLimite tipoLimite) {
        tipoLimiteRepository.delete(tipoLimite);
    }
}

