package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.exception.InvalidUnidadeMedidaException;
import school.sptech.sistema_estoque.exception.UnidadeMedidaNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.repository.UnidadeMedidaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeMedidaService {
    private final UnidadeMedidaRepository repository;
    public UnidadeMedidaService(UnidadeMedidaRepository repository) {
        this.repository = repository;
    }

    public UnidadeMedida cadastrarUnidadeMedida(UnidadeMedidaRequest request){
        if (request==null){throw new InvalidUnidadeMedidaException("Unidade de Medida Inválido");} // VALIDAÇÃO INICIAL
        UnidadeMedida unidade = new UnidadeMedida(null, request.nomeUnidade());
        return repository.save(unidade);
    }
    public List<UnidadeMedida> listarUnidadeMedida(){
        return repository.findAll();
    }
    public void excluirUnidadeMedida(Integer id){
        Optional<UnidadeMedida> opt = repository.findById(id);
        if (opt.isEmpty()){throw new UnidadeMedidaNaoExisteException("Material Não Encontrado");}
        repository.delete(opt.get());
    }
}
