package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;

public record AlmoxarifeResponse(
        @Schema(description = "ID do Almoxarife") Integer id,
        @Schema(description = "Nome do Almoxarife") String nome,
        @Schema(description = "Email do Almoxarife") String email,
        @Schema(description = "Telefone do Almoxarife") String telefone,
        @Schema(description = "Almoxarifado Associado") AlmoxarifadoResponse almoxarifado
) {}
