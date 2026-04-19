package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteResponse;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteResponse;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.model.estoque.TipoLimite;

public class LimiteMapper {
    public static TipoLimite toTipoLimiteEntity(TipoLimiteRequest request){
        TipoLimite tl = new TipoLimite();
        tl.setTipo(request.tipo());
        return tl;
    }

    public static TipoLimiteResponse toTipoLimiteResponse(TipoLimite entity){
        return new TipoLimiteResponse(entity.getId(), entity.getTipo());
    }

    public static Limite toLimiteEntity(LimiteRequest request, TipoLimite tipoLimite){
        Limite l = new Limite();
        l.setLimite(request.limite());
        l.setTipoLimite(tipoLimite);
        return l;
    }

    public static LimiteResponse toLimiteResponse(Limite entity){
        return new LimiteResponse(
                entity.getId(),
                entity.getLimite(),
                toTipoLimiteResponse(entity.getTipoLimite())
        );
    }
}
