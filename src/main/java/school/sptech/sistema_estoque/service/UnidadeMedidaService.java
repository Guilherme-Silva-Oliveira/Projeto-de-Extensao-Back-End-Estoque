package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.port.UnidadeMedidaPort;
import school.sptech.sistema_estoque.repository.UnidadeMedidaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeMedidaService {
    private final UnidadeMedidaPort unidadeMedidaPort;

    public UnidadeMedidaService(UnidadeMedidaPort unidadeMedidaPort) {
        this.unidadeMedidaPort = unidadeMedidaPort;
    }

    public UnidadeMedida cadastrarUnidadeMedida(UnidadeMedidaRequest request){
        if (request==null){throw new EntidadeInvalidException("Unidade de Medida Inválido");} // VALIDAÇÃO INICIAL
        UnidadeMedida unidade = new UnidadeMedida(null, request.nomeUnidade());
        return unidadeMedidaPort.save(unidade);
    }
    public List<UnidadeMedida> listarUnidadeMedida(){
        return unidadeMedidaPort.findAll();
    }
    public void excluirUnidadeMedida(Integer id){
        Optional<UnidadeMedida> opt = unidadeMedidaPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Material Não Encontrado");}
        unidadeMedidaPort.delete(opt.get());
    }
}
