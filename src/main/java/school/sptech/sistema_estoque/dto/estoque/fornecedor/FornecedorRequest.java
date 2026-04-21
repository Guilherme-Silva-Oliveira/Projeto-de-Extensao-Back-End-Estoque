package school.sptech.sistema_estoque.dto.estoque.fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedorRequest(
        @NotBlank @Schema(description = "Nome do Fornecedor",example = "Ricardo Alves") String nome,
        @NotBlank @Email @Schema(description = "Email do Fornecedor",example = "ricardoalves@gmail.com") String email,
        @NotBlank @Schema(description = "Telefone do Fornecedor",example = "11098765432") String telefone,
        @NotNull @Schema(description = "Fk do Tipo de Fornecedor",example = "1") Integer idTipoFornecedor
) {
}
