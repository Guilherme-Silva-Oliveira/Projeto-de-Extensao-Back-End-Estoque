package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.CategoriaRequest;
import school.sptech.sistema_estoque.dto.estoque.CategoriaResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.CategoriaNaoExisteException;
import school.sptech.sistema_estoque.exception.InvalidCategoriaRequestException;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;
    private final SistemaMapper mapper;
    public CategoriaService(CategoriaRepository repository, SistemaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CategoriaResponse cadastrarCategoria(CategoriaRequest request){
        if (request==null){throw new InvalidCategoriaRequestException("Categoria Inválida");} // VALIDAÇÃO INICIAL
        Categoria c = mapper.toCategoriaEntity(request); // CONVERSÃO REQUEST - ENTIDADE CATEGORIA
        Categoria salvo = repository.save(c);
        return mapper.toCategoriaResponse(salvo);
    }
    public List<CategoriaResponse> listarCategorias(){
        // CONVERTENDO ENTIDADE - RESPONSE CATEGORIA + EXIBIR
        return repository.findAll().stream()
                .map(mapper::toCategoriaResponse)
                .toList();
    }
    public void excluirCategoria(Integer id){
        Optional<Categoria> opt = repository.findById(id);
        if (opt.isEmpty()){throw new CategoriaNaoExisteException("Categoria Não Encontrada");}
        repository.delete(opt.get());
    }
}
