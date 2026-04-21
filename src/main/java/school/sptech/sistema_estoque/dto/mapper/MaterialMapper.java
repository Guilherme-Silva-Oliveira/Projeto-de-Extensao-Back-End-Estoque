package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.material.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;

public class MaterialMapper {
    public static Material toEntity(MaterialRequest request, Categoria categoria, Almoxarifado almoxarifado, UnidadeMedida unidadeMedida){
        Material m = new Material();
        m.setCategoria(categoria);
        m.setAlmoxarifado(almoxarifado);
        m.setUnidadeMedida(unidadeMedida);
        m.setNomeMaterial(request.nomeMaterial());
        m.setQuantidade(0);
        return m;
    }

    public static MaterialResponse toResponse(Material entity){
        return new MaterialResponse(
                entity.getId(),
                CategoriaMapper.toResponse(entity.getCategoria()),
                UnidadeMedidaMapper.toResponse(entity.getUnidadeMedida()),
                AlmoxarifadoMapper.toResponse(entity.getAlmoxarifado()),
                entity.getNomeMaterial()
        );
    }
}
