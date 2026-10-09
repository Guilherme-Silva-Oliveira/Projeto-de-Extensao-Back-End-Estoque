package school.sptech.sistema_estoque.dto.estoque.limite;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.model.estoque.Material;

public record LimiteResponse(
        @Schema(description = "ID do Limite") Integer id,
        @Schema(description = "Descrição do Limite") String descLimite,
        @Schema(description = "Valor do Limite") Double limite,
        @Schema(description = "Material Associado") Material material
) {}