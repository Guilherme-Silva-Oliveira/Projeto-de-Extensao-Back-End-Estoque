package school.sptech.sistema_estoque.dto.estoque.escala;

import io.swagger.v3.oas.annotations.media.Schema;

public record EscalaResponse(
        @Schema(description = "ID da Escala") Integer id,
        @Schema(description = "Nome da Escala") String nomeEscala
) {
}
