package school.sptech.sistema_estoque.dto.estoque.almoxarifado;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteResponse;

import java.util.List;

public record AlmoxarifadoResponse(
        @Schema(description = "Identificador do Almoxarifado",example = "1") Integer id,
        @Schema(description = "Número da Sala do Almoxarifado",example = "201") Integer numeroSala
) {}
