package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlmoxarifeLogin(
    @NotBlank @Email @Schema(description = "Email do Almoxarife",example = "marisa@gmail.com") String email,
    @NotBlank @Size(min = 6) @Schema(description = "Senha do Almoxarife",example = "xingu1234") String senha
) {
}
