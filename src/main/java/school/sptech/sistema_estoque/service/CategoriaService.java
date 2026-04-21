package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
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
        if (request==null){throw new EntidadeInvalidException("Categoria Inválida");}
        Categoria c = new Categoria(null, request.nomeCategoria());
        return repository.save(c);
    }

    public List<Categoria> listarCategorias(){
        return repository.findAll();
    }

    public void excluirCategoria(Integer id){
        Optional<Categoria> opt = repository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Categoria Não Encontrada");}
        repository.delete(opt.get());
    }
}
