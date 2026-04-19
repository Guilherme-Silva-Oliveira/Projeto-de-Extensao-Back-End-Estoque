package school.sptech.sistema_estoque.dto.estoque.tipo_limite;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TipoLimiteRequest(
        @NotBlank @Schema(description = "Nome do Tipo do Limite") String tipo
) {
}
