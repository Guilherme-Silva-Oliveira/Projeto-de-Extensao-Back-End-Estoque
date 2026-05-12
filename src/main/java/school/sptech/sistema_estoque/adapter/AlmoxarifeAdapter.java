
package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.port.AlmoxarifePort;
import school.sptech.sistema_estoque.repository.AlmoxarifeRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AlmoxarifeAdapter implements AlmoxarifePort {
    private final AlmoxarifeRepository repository;

    public AlmoxarifeAdapter(AlmoxarifeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Almoxarife save(Almoxarife almoxarife) {
        return repository.save(almoxarife);
    }

    @Override
    public List<Almoxarife> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Almoxarife> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Almoxarife> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Almoxarife almoxarife) {
        repository.delete(almoxarife);
    }

    @Override
    public boolean existsByEmailAndAlmoxarifadoId(String email, Integer almoxarifadoId) {
        return repository.existsByEmailAndAlmoxarifadoId(email, almoxarifadoId);
    }

}

