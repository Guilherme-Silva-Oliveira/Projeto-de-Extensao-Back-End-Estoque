package school.sptech.sistema_estoque.dto.estoque.motivo;

import jakarta.validation.constraints.NotBlank;

public record MotivoRequest (
        @NotBlank String descricao
){}
