package school.sptech.sistema_estoque.dto.estoque.material;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaterialRequest(
        @NotNull @Schema(description = "Fk para Categoria",example = "1") Integer idCategoria,
        @NotNull @Schema(description = "Fk para Almoxarifado",example = "1") Integer idAlmoxarifado,
        @NotBlank @Schema(description = "Nome do Material",example = "Papel Sulfite") String nomeMaterial,
        @NotBlank @Schema(description = "Código de Barras do Material",example = "670981205") String codigoBarras,
        @NotNull @Schema(description = "Fk para Unidade de Medida",example = "1") Integer idUnidadeMedida
){}
