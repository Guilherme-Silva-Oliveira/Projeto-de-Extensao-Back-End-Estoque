
package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.port.CategoriaPort;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaPort categoriaPort;

    public CategoriaService(CategoriaPort categoriaPort) {
        this.categoriaPort = categoriaPort;
    }

    public Categoria cadastrarCategoria(CategoriaRequest request){
        if (request == null) {throw new EntidadeInvalidException("Categoria Inválida");}
        if (categoriaPort.findByNomeCategoria(request.nomeCategoria()).isPresent()) {throw new ResponseStatusException(HttpStatus.CONFLICT, "Categoria com esse nome já existe");}
        Categoria c = new Categoria(null, request.nomeCategoria());
        return categoriaPort.save(c);
    }

    public List<Categoria> listarCategorias(){
        return categoriaPort.findAll();
    }

    public void excluirCategoria(Integer id){
        Optional<Categoria> opt = categoriaPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Categoria Não Encontrada");}
        categoriaPort.delete(opt.get());
    }
}
