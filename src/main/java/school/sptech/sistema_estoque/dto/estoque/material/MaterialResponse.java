package school.sptech.sistema_estoque.dto.estoque.material;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaResponse;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaResponse;

public record MaterialResponse(
        @Schema(description = "ID para o Material") Integer id,
        @Schema(description = "Categoria Associada") CategoriaResponse categoria,
        @Schema(description = "Unidade de Medida Associada") UnidadeMedidaResponse unidadeMedida,
        @Schema(description = "Almoxarifado Associado") AlmoxarifadoResponse almoxarifado,
        @Schema(description = "Nome do Material") String nomeMaterial,
        @Schema(description = "Código de Barras") String codigoBarras
) {}
