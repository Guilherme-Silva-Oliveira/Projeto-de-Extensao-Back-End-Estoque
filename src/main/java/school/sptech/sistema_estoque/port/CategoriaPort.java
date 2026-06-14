package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaPort {
    Categoria save(Categoria categoria);
    List<Categoria> findAll();
    Optional<Categoria> findByNomeCategoria(String nomeCategoria);
    Optional<Categoria> findById(Integer id);
    void delete(Categoria categoria);

}