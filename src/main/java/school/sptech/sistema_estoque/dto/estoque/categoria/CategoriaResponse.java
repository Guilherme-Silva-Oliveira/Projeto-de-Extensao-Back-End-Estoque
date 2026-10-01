package school.sptech.sistema_estoque.dto.estoque.categoria;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategoriaResponse(
        @Schema(description = "Nome da Categoria") String nomeCategoria
) {}
