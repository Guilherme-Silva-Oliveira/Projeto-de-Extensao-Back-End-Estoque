package school.sptech.sistema_estoque.dto.estoque.tipo_limite;

import io.swagger.v3.oas.annotations.media.Schema;

public record TipoLimiteResponse(
        @Schema(description = "ID do Tipo do Limite") Integer id,
        @Schema(description = "Nome do Tipo do Limite") String tipo
) {
}
