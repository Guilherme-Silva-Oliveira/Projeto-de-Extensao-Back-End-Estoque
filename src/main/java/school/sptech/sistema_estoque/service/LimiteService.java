package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.limite.LimitePatchDto;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.TipoLimite;
import school.sptech.sistema_estoque.port.LimitePort;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.port.TipoLimitePort;

import java.util.List;
import java.util.Optional;

@Service
public class LimiteService {
    private final LimitePort limitePort;
    private final TipoLimitePort tipoLimitePort;
    private final MaterialPort materialPort;

    public LimiteService(LimitePort limitePort, TipoLimitePort tipoLimitePort, MaterialPort materialPort) {
        this.limitePort = limitePort;
        this.tipoLimitePort = tipoLimitePort;
        this.materialPort = materialPort;
    }

    public TipoLimite cadastrarTipoLimite(TipoLimiteRequest request){
        if (request == null){ throw new EntidadeInvalidException("Tipo de Limite Inválido"); }
        TipoLimite tl = new TipoLimite(null, request.tipo());
        return tipoLimitePort.save(tl);
    }

    public List<TipoLimite> listarTiposLimite(){
        return tipoLimitePort.findAll();
    }

    public void excluirTipoLimite(Integer id){
        Optional<TipoLimite> opt = tipoLimitePort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Tipo Limite Não Encontrado");}
        tipoLimitePort.delete(opt.get());
    }

    public Limite cadastrarLimite(LimiteRequest request){
        if (request == null){ throw new EntidadeInvalidException("Limite Inválido"); }
        Optional<TipoLimite> tipoOptional = tipoLimitePort.findById(request.idTipoLimite());
        if (tipoOptional.isEmpty()){ throw new EntidadeInvalidException("Tipo de Limite não encontrado"); }
        Optional<Material> materialOptional = materialPort.findById(request.idMaterial());
        if (materialOptional.isEmpty()) { throw new EntidadeInvalidException("Material não encontrado"); }
        Limite l = new Limite(null, request.limite(), tipoOptional.get(), materialOptional.get());
        return limitePort.save(l);
    }

    public List<Limite> listarLimites(){
        return limitePort.findAll();
    }

    public void excluirLimite(Integer id){
        Optional<Limite> opt = limitePort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Limite Não Encontrado");}
        limitePort.delete(opt.get());
    }

    public Limite atualizarLimite(Integer id, LimitePatchDto dto){

        Optional<Limite> opt = limitePort.findById(id);

        if(opt.isEmpty()){
            throw new EntidadeNaoExisteException("Limite Não Encontrado");
        }

        Limite limite = opt.get();

        if(dto.valorLimite() != null){
            limite.setLimite(dto.valorLimite());
        }

        return limitePort.save(limite);
    }
}