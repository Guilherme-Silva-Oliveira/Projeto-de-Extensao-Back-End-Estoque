package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.lista_material.ListaMaterialResponse;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;

public class ListaMaterialMapper {

    public static ListaMaterialResponse toResponse(ListaMaterial listaMaterial){
        return new ListaMaterialResponse(
                listaMaterial.getId(),
                listaMaterial.getMaterial().getNomeMaterial(),
                listaMaterial.getQuantidade(),
                listaMaterial.getMaterial().getQuantidade(),
                listaMaterial.getReservado()
        );
    }
}
