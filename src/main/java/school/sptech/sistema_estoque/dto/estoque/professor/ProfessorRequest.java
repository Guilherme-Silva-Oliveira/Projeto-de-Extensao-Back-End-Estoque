package school.sptech.sistema_estoque.dto.estoque.professor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequest(
         @NotBlank @Schema(description = "Nome do Professor",example = "Rogério Santos") String nome,
         @NotBlank @Email @Schema(description = "Email do Professor",example = "rogeriosantos@gmail.com") String email,
         @NotBlank @Schema(description = "Telefone do Professor",example = "11223334455") String telefone
) {
}
