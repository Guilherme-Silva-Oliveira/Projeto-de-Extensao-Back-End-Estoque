package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AlmoxarifadoAdapter implements AlmoxarifadoPort {
    private final AlmoxarifadoRepository repository;

    public AlmoxarifadoAdapter(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Almoxarifado save(Almoxarifado almoxarifado) {
        return repository.save(almoxarifado);
    }

    @Override
    public List<Almoxarifado> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Almoxarifado> findByNumeroSala(Integer numeroSala) {
        return repository.findByNumeroSala(numeroSala);
    }

    @Override
    public Optional<Almoxarifado> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Almoxarifado almoxarifado) {
        repository.delete(almoxarifado);
    }

}
