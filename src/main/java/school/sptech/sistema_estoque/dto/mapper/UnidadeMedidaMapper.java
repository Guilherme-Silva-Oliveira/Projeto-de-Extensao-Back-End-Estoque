package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaResponse;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;

public class UnidadeMedidaMapper {
    public static UnidadeMedida toEntity(UnidadeMedidaRequest request){
        UnidadeMedida u = new UnidadeMedida();
        u.setNomeUnidade(request.nomeUnidade());
        return u;
    }
    public static UnidadeMedidaResponse toResponse(UnidadeMedida entity){
        return new UnidadeMedidaResponse(entity.getId(),entity.getNomeUnidade());
    }
}
