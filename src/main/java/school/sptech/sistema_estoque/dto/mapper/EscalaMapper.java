package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.escala.EscalaRequest;
import school.sptech.sistema_estoque.dto.estoque.escala.EscalaResponse;
import school.sptech.sistema_estoque.model.estoque.Escala;

public class EscalaMapper {
    public static Escala toEntity(EscalaRequest request){
        Escala e = new Escala();
        e.setNomeEscala(request.nomeEscala());
        return e;
    }

    public static EscalaResponse toResponse(Escala entity){
        return new EscalaResponse(entity.getId(), entity.getNomeEscala());
    }
}
