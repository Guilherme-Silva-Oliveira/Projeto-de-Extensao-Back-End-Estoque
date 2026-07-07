package school.sptech.sistema_estoque.dto.estoque.material;

public record MaterialUpdateRequest(
        String nomeMaterial,
        Integer quantidade,
        String descricao
) {
}
