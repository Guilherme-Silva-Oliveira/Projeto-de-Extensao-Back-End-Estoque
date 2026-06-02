package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.motivo.MotivoRequest;
import school.sptech.sistema_estoque.dto.estoque.motivo.MotivoResponse;
import school.sptech.sistema_estoque.model.estoque.*;

public class MotivoMapper {
    public static Motivo toEntity(MotivoRequest request){
        Motivo motivo = new Motivo();
        motivo.setDescricao(request.descricao());
        return motivo;
    }

    public static MotivoResponse toResponse(Motivo motivo){
        return new MotivoResponse(motivo.getId(),motivo.getDescricao());
    }
}
