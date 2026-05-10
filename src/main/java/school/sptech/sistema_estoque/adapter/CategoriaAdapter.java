
package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.port.CategoriaPort;
import school.sptech.sistema_estoque.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriaAdapter implements CategoriaPort {
    private final CategoriaRepository repository;

    public CategoriaAdapter(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Categoria> findByNomeCategoria(String nomeCategoria) {
        return repository.findByNomeCategoria(nomeCategoria);
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Categoria categoria) {
        repository.delete(categoria);
    }

}

