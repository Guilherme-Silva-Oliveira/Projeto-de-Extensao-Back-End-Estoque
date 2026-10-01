package school.sptech.sistema_estoque.dto.estoque.unidade_medida;

import io.swagger.v3.oas.annotations.media.Schema;

public record UnidadeMedidaResponse(
        @Schema(description = "Nome da Unidade de Medida") String nomeUnidade
) {}
