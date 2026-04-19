package school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TipoFornecedorRequest(
        @NotBlank @Schema(description = "Nome do Tipo de Fornecedor",example = "Papelaria Cores") String nomeTipo
) {
}
