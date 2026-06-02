package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Motivo;
import school.sptech.sistema_estoque.port.MotivoPort;
import school.sptech.sistema_estoque.repository.MotivoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class MotivoAdapter implements MotivoPort {
    private final MotivoRepository repository;
    public MotivoAdapter(MotivoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Motivo save(Motivo motivo) {
        return repository.save(motivo);
    }

    @Override
    public List<Motivo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Motivo> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Motivo motivo) {
        repository.delete(motivo);
    }
}
