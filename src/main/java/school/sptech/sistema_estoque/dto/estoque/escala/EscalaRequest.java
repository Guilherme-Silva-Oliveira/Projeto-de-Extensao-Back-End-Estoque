package school.sptech.sistema_estoque.dto.estoque.escala;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record EscalaRequest(
        @NotBlank @Schema(description = "Nome da Escala",example = "Mínimo") String nomeEscala
) {
}
