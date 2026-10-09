package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteResponse;
import school.sptech.sistema_estoque.model.estoque.Limite;

public class LimiteMapper {

    public static Limite toLimiteEntity(LimiteRequest request){
        Limite l = new Limite();
        l.setLimite(request.limite());
        l.setDescLimite(request.descLimite());
        return l;
    }

    public static LimiteResponse toLimiteResponse(Limite entity){
        return new LimiteResponse(
                entity.getId(),
                entity.getDescLimite(),
                entity.getLimite(),
                entity.getMaterial()
        );
    }
}
