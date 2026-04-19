package school.sptech.sistema_estoque.dto.estoque.almoxarifado;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AlmoxarifadoRequest(
        @NotNull @Schema(description = "Numero da Sala do Almoxarifado",example = "201") Integer numeroSala
) {}
