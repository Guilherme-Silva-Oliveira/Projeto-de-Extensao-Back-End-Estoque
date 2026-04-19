package school.sptech.sistema_estoque.dto.estoque.professor;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProfessorResponse(
        @Schema(description = "ID do Professor")Integer id,
        @Schema(description = "Nome do Professor") String nome,
        @Schema(description = "Email do Professor") String email,
        @Schema(description = "Telefone do Professor") String telefone
) {
}
