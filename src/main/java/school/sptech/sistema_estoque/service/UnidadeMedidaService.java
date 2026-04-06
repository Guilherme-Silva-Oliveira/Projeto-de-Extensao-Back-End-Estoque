package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.InvalidUnidadeMedidaException;
import school.sptech.sistema_estoque.exception.UnidadeMedidaNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.repository.UnidadeMedidaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeMedidaService {
    private final UnidadeMedidaRepository repository;
    private final SistemaMapper mapper;
    public UnidadeMedidaService(UnidadeMedidaRepository repository, SistemaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UnidadeMedidaResponse cadastrarUnidadeMedida(UnidadeMedidaRequest request){
        if (request==null){throw new InvalidUnidadeMedidaException("Unidade de Medida Inválido");} // VALIDAÇÃO INICIAL
        UnidadeMedida unidade = mapper.toUnidadeMedidaEntity(request);
        repository.save(unidade);
        return mapper.toUnidadeMedidaResponse(unidade);
    }
    public List<UnidadeMedidaResponse> listarUnidadeMedida(){
        return repository.findAll().stream()
                .map(mapper::toUnidadeMedidaResponse)
                .toList();
    }
    public void excluirUnidadeMedida(Integer id){
        Optional<UnidadeMedida> opt = repository.findById(id);
        if (opt.isEmpty()){throw new UnidadeMedidaNaoExisteException("Material Não Encontrado");}
        repository.delete(opt.get());
    }
}
