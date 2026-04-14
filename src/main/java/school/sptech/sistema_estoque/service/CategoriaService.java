package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.CategoriaRequest;
import school.sptech.sistema_estoque.exception.CategoriaNaoExisteException;
import school.sptech.sistema_estoque.exception.InvalidCategoriaRequestException;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria cadastrarCategoria(CategoriaRequest request){
        if (request==null){throw new InvalidCategoriaRequestException("Categoria Inválida");} // VALIDAÇÃO INICIAL
        Categoria c = new Categoria(null, request.nomeCategoria()); // CONVERSÃO REQUEST - ENTIDADE CATEGORIA
        return repository.save(c);
    }
    public List<Categoria> listarCategorias(){
        // RETORNANDO ENTIDADES CATEGORIA
        return repository.findAll();
    }
    public void excluirCategoria(Integer id){
        Optional<Categoria> opt = repository.findById(id);
        if (opt.isEmpty()){throw new CategoriaNaoExisteException("Categoria Não Encontrada");}
        repository.delete(opt.get());
    }
}
