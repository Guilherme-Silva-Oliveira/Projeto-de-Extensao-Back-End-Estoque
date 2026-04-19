package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaRequest;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaResponse;
import school.sptech.sistema_estoque.model.estoque.Categoria;

public class CategoriaMapper {
    public static Categoria toEntity(CategoriaRequest request){
        Categoria c = new Categoria();
        c.setNomeCategoria(request.nomeCategoria());
        return c;
    }
    public static CategoriaResponse toResponse(Categoria entity){
        return new CategoriaResponse(entity.getId(),entity.getNomeCategoria());
    }
}
