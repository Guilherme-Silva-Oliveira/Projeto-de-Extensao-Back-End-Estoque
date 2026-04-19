package school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;

public record TipoFornecedorResponse(
        @Schema(description = "ID do Tipo do Fornecedor") Integer id,
        @Schema(description = "Nome do Tipo do Fornecedor") String nomeTipo
) {
}
