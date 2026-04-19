package school.sptech.sistema_estoque.dto.estoque.limite;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteResponse;

public record LimiteResponse(
        @Schema(description = "ID do Limite") Integer id,
        @Schema(description = "Valor do Limite") String limite,
        @Schema(description = "Tipo do Limite Associado") TipoLimiteResponse tipoLimite
) {}