package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.port.LimitePort;
import school.sptech.sistema_estoque.repository.LimiteRepository;
import java.util.List;
import java.util.Optional;

@Component
public class LimiteAdapter implements LimitePort {
    private final LimiteRepository limiteRepository;

    public LimiteAdapter(LimiteRepository limiteRepository) {
        this.limiteRepository = limiteRepository;
    }

    @Override
    public Limite save(Limite limite) {
        return limiteRepository.save(limite);
    }

    @Override
    public List<Limite> findAll() {
        return limiteRepository.findAll();
    }

    @Override
    public Optional<Limite> findById(Integer id) {
        return limiteRepository.findById(id);
    }

    @Override
    public void delete(Limite limite) {
        limiteRepository.delete(limite);
    }
}
