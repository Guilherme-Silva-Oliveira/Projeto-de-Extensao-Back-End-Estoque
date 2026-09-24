package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AlmoxarifeRequest(
        @NotBlank @Schema(description = "Nome do Almoxarife",example = "Marisa") String nome,
        @NotBlank @Email @Schema(description = "Email do Almoxarife",example = "marisa@gmail.com") String email,
        @NotBlank @Schema(description = "Telefone do Almoxarife",example = "11981234350") String telefone,
        @Schema(description = "Status do Usuário",example = "true") Boolean statusUsuario,
        @NotBlank @Size(min = 6) @Schema(description = "Senha do Almoxarife",example = "xingu1234") String senha,
        @NotNull @Schema(description = "Fk para Almoxarifado",example = "1") Integer idAlmoxarifado
) {
}
