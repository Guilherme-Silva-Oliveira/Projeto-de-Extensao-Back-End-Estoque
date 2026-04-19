package school.sptech.sistema_estoque.dto.estoque.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CategoriaRequest(
        @NotBlank @Schema(description = "Nome da Categoria",example = "Papéis") String nomeCategoria
) {}
