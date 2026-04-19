package school.sptech.sistema_estoque.dto.estoque.unidade_medida;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UnidadeMedidaRequest(
        @NotBlank @Schema(description = "Nome da Unidade de Medida",example = "Unidade") String nomeUnidade
) {}
