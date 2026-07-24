package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.material.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;
import school.sptech.sistema_estoque.model.estoque.*;

public class MaterialMapper {
    public static Material toEntity(MaterialRequest request, Categoria categoria, Almoxarifado almoxarifado, UnidadeMedida unidadeMedida, SetorEstoque setor){
        Material m = new Material();
        m.setCategoria(categoria);
        m.setAlmoxarifado(almoxarifado);
        m.setUnidadeMedida(unidadeMedida);
        m.setNomeMaterial(request.nomeMaterial());
        m.setQuantidade(0);
        m.setDescricao(request.descricao());
        m.setSetor(setor);
        return m;
    }

    public static MaterialResponse toResponse(Material entity){
        return new MaterialResponse(
                entity.getId(),
                CategoriaMapper.toResponse(entity.getCategoria()),
                UnidadeMedidaMapper.toResponse(entity.getUnidadeMedida()),
                AlmoxarifadoMapper.toResponse(entity.getAlmoxarifado()),
                entity.getNomeMaterial(),
                entity.getQuantidade(),
                entity.getDescricao()
        );
    }
}
