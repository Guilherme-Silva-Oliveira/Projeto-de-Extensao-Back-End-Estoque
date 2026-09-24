package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.lista_material.ListaMaterialResponse;
import school.sptech.sistema_estoque.dto.estoque.motivo.MotivoRequest;
import school.sptech.sistema_estoque.dto.estoque.motivo.MotivoResponse;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;
import school.sptech.sistema_estoque.model.estoque.Motivo;

public class ListaMaterialMapper {

    public static ListaMaterialResponse toResponse(ListaMaterial listaMaterial){
        return new ListaMaterialResponse(
                listaMaterial.getMaterial().getNomeMaterial(),
                listaMaterial.getQuantidade(),
                listaMaterial.getMaterial().getQuantidade()
        );
    }
}
