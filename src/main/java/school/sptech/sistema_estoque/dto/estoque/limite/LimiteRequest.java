package school.sptech.sistema_estoque.dto.estoque.limite;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LimiteRequest(
        @NotBlank @Schema(description = "Descrição do Limite",example = "Limite Mínimo") String descLimite,
        @NotBlank @Schema(description = "Valor do Limite",example = "10.0") Double limite,
        @NotNull @Schema(description = "Fk do Material") Integer idMaterial
) {}
