package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.model.estoque.Categoria;
import school.sptech.sistema_estoque.model.estoque.Estoque;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;

public class SistemaMapper {
    // CATEGORIA
    public Categoria toCategoriaEntity(CategoriaRequest request){
        Categoria c = new Categoria();
        c.setNomeCategoria(request.nomeCategoria());
        return c;
    }
    public CategoriaResponse toCategoriaResponse(Categoria entity){
        return new CategoriaResponse(entity.getId(),entity.getNomeCategoria());
    }

    // MATERIAL
    public Material toMaterialEntity(MaterialRequest request, Categoria categoria, Estoque estoque, UnidadeMedida unidadeMedida){
        Material m = new Material();
        m.setCategoria(categoria);
        m.setEstoque(estoque);
        m.setUnidadeMedida(unidadeMedida);
        return m;
    }
    public MaterialResponse toMaterialResponse(Material entity){
        return new MaterialResponse(entity.getId(),entity.getCategoria());
    }

    // UNIDADE DE MEDIDA
    public UnidadeMedida toUnidadeMedidaEntity(UnidadeMedidaRequest request){
        UnidadeMedida u = new UnidadeMedida();
        u.setNomeUnidade(request.nomeUnidade());
        return u;
    }
    public UnidadeMedidaResponse toUnidadeMedidaResponse(UnidadeMedida entity){
        return new UnidadeMedidaResponse(entity.getId(),entity.getNomeUnidade());
    }
}
