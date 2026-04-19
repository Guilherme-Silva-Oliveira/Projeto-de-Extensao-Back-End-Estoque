package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteRequest;
import school.sptech.sistema_estoque.exception.*;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.model.estoque.TipoLimite;
import school.sptech.sistema_estoque.repository.LimiteRepository;
import school.sptech.sistema_estoque.repository.TipoLimiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LimiteService {
    private final LimiteRepository limrepository;
    private final TipoLimiteRepository tplrepository;
    public LimiteService(LimiteRepository limrepository, TipoLimiteRepository tplrepository) {
        this.limrepository = limrepository;
        this.tplrepository = tplrepository;
    }

    public TipoLimite cadastrarTipoLimite(TipoLimiteRequest request){
        if (request == null){ throw new EntidadeInvalidException("Tipo de Limite Inválido"); }
        TipoLimite tl = new TipoLimite(null, request.tipo());
        return tplrepository.save(tl);
    }

    public List<TipoLimite> listarTiposLimite(){
        return tplrepository.findAll();
    }

    public void excluirTipoLimite(Integer id){
        Optional<TipoLimite> opt = tplrepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Tipo Limite Não Encontrado");}
        tplrepository.delete(opt.get());
    }

    // LIMITES
    public Limite cadastrarLimite(LimiteRequest request){
        if (request == null){ throw new EntidadeInvalidException("Limite Inválido"); }
        Optional<TipoLimite> tipoOptional = tplrepository.findById(request.idTipoLimite());
        if (tipoOptional.isEmpty()){ throw new EntidadeInvalidException("Tipo de Limite não encontrado"); }
        Limite l = new Limite(null, request.limite(), tipoOptional.get());
        return limrepository.save(l);
    }

    public List<Limite> listarLimites(){
        return limrepository.findAll();
    }

    public void excluirLimite(Integer id){
        Optional<Limite> opt = limrepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Limite Não Encontrado");}
        limrepository.delete(opt.get());
    }
}
