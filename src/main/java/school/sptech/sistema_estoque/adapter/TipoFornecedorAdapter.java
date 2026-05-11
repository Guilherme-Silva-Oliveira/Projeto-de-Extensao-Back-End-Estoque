package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;
import school.sptech.sistema_estoque.port.TipoFornecedorPort;
import school.sptech.sistema_estoque.repository.TipoFornecedorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TipoFornecedorAdapter implements TipoFornecedorPort {
    private final TipoFornecedorRepository repository;

    public TipoFornecedorAdapter(TipoFornecedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public TipoFornecedor save(TipoFornecedor tipoFornecedor) {
        return repository.save(tipoFornecedor);
    }

    @Override
    public List<TipoFornecedor> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TipoFornecedor> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(TipoFornecedor tipoFornecedor) {
        repository.delete(tipoFornecedor);
    }
}
