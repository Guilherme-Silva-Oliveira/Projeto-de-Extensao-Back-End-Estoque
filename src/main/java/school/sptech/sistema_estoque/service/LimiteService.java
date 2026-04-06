package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.LimiteResponse;
import school.sptech.sistema_estoque.dto.estoque.TipoLimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.TipoLimiteResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
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
    private final SistemaMapper mapper;
    public LimiteService(LimiteRepository limrepository, TipoLimiteRepository tplrepository, SistemaMapper mapper) {
        this.limrepository = limrepository;
        this.tplrepository = tplrepository;
        this.mapper = mapper;
    }

    public TipoLimiteResponse cadastrarTipoLimite(TipoLimiteRequest request){
        if (request == null){ throw new InvalidTipoLimiteRequestException("Tipo de Limite Inválido"); } // VALIDAÇÃO INICIAL

        TipoLimite tl = mapper.toTipoLimiteEntity(request); // CONVERSÃO REQUEST - ENTIDADE TIPO LIMITE
        TipoLimite salvo = tplrepository.save(tl);

        return mapper.toTipoLimiteResponse(salvo);
    }

    public List<TipoLimiteResponse> listarTiposLimite(){
        // CONVERTENDO ENTIDADE - RESPONSE TIPO LIMITE + EXIBIR
        return tplrepository.findAll().stream()
                .map(mapper::toTipoLimiteResponse)
                .toList();
    }

    public LimiteResponse cadastrarLimite(LimiteRequest request){
        if (request == null){ throw new InvalidLimiteRequestException("Limite Inválido"); } // VALIDAÇÃO INICIAL

        // VALIDAÇÃO DA EXISTÊNCIA DO TIPO LIMITE
        Optional<TipoLimite> tipoOptional = tplrepository.findById(request.idTipoLimite());
        if (tipoOptional.isEmpty()){ throw new InvalidTipoLimiteRequestException("Tipo de Limite não encontrado"); }

        Limite l = mapper.toLimiteEntity(request, tipoOptional.get()); // CONVERSÃO REQUEST - ENTIDADE LIMITE
        Limite salvo = limrepository.save(l);

        return mapper.toLimiteResponse(salvo);
    }

    public List<LimiteResponse> listarLimites(){
        // CONVERTENDO ENTIDADE - RESPONSE LIMITE + EXIBIR
        return limrepository.findAll().stream()
                .map(mapper::toLimiteResponse)
                .toList();
    }
}
