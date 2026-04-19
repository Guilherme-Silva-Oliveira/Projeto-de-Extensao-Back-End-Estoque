package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteRequest;
import school.sptech.sistema_estoque.exception.InvalidLimiteRequestException;
import school.sptech.sistema_estoque.exception.InvalidTipoLimiteRequestException;
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
        if (request == null){ throw new InvalidTipoLimiteRequestException("Tipo de Limite Inválido"); } // VALIDAÇÃO INICIAL

        TipoLimite tl = new TipoLimite(null, request.tipo()); // CONVERSÃO REQUEST - ENTIDADE TIPO LIMITE
        return tplrepository.save(tl);
    }

    public List<TipoLimite> listarTiposLimite(){
        // RETORNANDO ENTIDADES TIPO LIMITE
        return tplrepository.findAll();
    }

    public Limite cadastrarLimite(LimiteRequest request){
        if (request == null){ throw new InvalidLimiteRequestException("Limite Inválido"); } // VALIDAÇÃO INICIAL

        // VALIDAÇÃO DA EXISTÊNCIA DO TIPO LIMITE
        Optional<TipoLimite> tipoOptional = tplrepository.findById(request.idTipoLimite());
        if (tipoOptional.isEmpty()){ throw new InvalidTipoLimiteRequestException("Tipo de Limite não encontrado"); }

        Limite l = new Limite(null, request.limite(), tipoOptional.get()); // CONVERSÃO REQUEST - ENTIDADE LIMITE
        return limrepository.save(l);
    }

    public List<Limite> listarLimites(){
        // RETORNANDO ENTIDADES LIMITE
        return limrepository.findAll();
    }
}
