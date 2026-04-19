package school.sptech.sistema_estoque.dto.estoque.fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorResponse;

public record FornecedorResponse(
        @Schema(description = "ID do Fornecedor") Integer id,
        @Schema(description = "Nome do Fornecedor") String nome,
        @Schema(description = "Email do Fornecedor") String email,
        @Schema(description = "Telefone do Fornecedor") String telefone,
        @Schema(description = "CNPJ ou CPF do Fornecedor") String cnpjCpf,
        @Schema(description = "Tipo do Fornecedor") TipoFornecedorResponse tipoFornecedor
) {
}
