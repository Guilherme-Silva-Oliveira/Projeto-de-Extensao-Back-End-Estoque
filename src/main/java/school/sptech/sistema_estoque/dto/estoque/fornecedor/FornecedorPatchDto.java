package school.sptech.sistema_estoque.dto.estoque.fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorResponse;

public record FornecedorPatchDto(

        @Schema(description = "Nome do Fornecedor", example = "Ricardo Alves")
        String nome,

        @Email
        @Schema(description = "Email do Fornecedor", example = "ricardoalves@gmail.com")
        String email,

        @Schema(description = "Telefone do Fornecedor", example = "11987654321")
        String telefone,

        @Schema(description = "Tipo do Fornecedor")
        TipoFornecedorResponse tipoFornecedor

) {
}