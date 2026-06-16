package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.limite.LimitePatchDto;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;
import school.sptech.sistema_estoque.model.estoque.TipoLimite;
import school.sptech.sistema_estoque.port.LimitePort;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.port.TipoLimitePort;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LimiteService {
    private final LimitePort limitePort;
    private final TipoLimitePort tipoLimitePort;
    private final MaterialPort materialPort;

    public TipoLimite cadastrarTipoLimite(TipoLimiteRequest request){
        if (request == null){ throw new EntidadeInvalidException("Tipo de Limite Inválido"); }
        TipoLimite tl = new TipoLimite(null, request.tipo());
        return tipoLimitePort.save(tl);
    }

    public List<TipoLimite> listarTiposLimite(){
        return tipoLimitePort.findAll();
    }

    public void excluirTipoLimite(Integer id){
        TipoLimite tipoLimite = tipoLimitePort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Tipo Limite Não Encontrado"));
        tipoLimitePort.delete(tipoLimite);
    }

    public Limite cadastrarLimite(LimiteRequest request){
        if (request == null){ throw new EntidadeInvalidException("Limite Inválido"); }
        TipoLimite tipoLimite = tipoLimitePort.findById(request.idMaterial()).orElseThrow(()-> new EntidadeNaoExisteException("Tipo Limite Não Encontrado"));
        Material material = materialPort.findById(request.idMaterial()).orElseThrow(()-> new EntidadeNaoExisteException("Material Não Encontrado"));
        Limite l = new Limite(null, request.limite(), tipoLimite, material);
        return limitePort.save(l);
    }

    public List<Limite> listarLimites(){
        return limitePort.findAll();
    }

    public void excluirLimite(Integer id){
        Limite limite = limitePort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Limite Não Encontrado"));
        limitePort.delete(limite);
    }

    public Limite atualizarLimite(Integer id, LimitePatchDto dto){
        Limite limite = limitePort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Limite Não Encontrado"));
        if(dto.valorLimite() != null){limite.setLimite(dto.valorLimite());}
        return limitePort.save(limite);
    }
}