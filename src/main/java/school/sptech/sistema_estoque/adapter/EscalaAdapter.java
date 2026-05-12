package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.port.EscalaPort;
import school.sptech.sistema_estoque.repository.EscalaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class EscalaAdapter implements EscalaPort {
    private final EscalaRepository repository;

    public EscalaAdapter(EscalaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Escala save(Escala escala) {
        return repository.save(escala);
    }

    @Override
    public List<Escala> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Escala> findByNomeEscala(String nomeEscala) {
        return repository.findByNomeEscala(nomeEscala);
    }

    @Override
    public Optional<Escala> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Escala escala) {
        repository.delete(escala);
    }

}
