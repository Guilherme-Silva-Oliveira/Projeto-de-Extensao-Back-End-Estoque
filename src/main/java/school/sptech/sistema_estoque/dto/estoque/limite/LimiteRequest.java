package school.sptech.sistema_estoque.dto.estoque.limite;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LimiteRequest(
        @NotBlank @Schema(description = "Valor do Limite",example = "500") String limite,
        @NotNull @Schema(description = "Fk do Tipo do Limite") Integer idTipoLimite
) {}