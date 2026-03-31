package school.sptech.sistema_estoque.dto.estoque;

public record MaterialRequest(
        Integer idCategoria,
        Integer idEstoque,
        String nomeMaterial,
        Integer idUnidadeMedida
){}
