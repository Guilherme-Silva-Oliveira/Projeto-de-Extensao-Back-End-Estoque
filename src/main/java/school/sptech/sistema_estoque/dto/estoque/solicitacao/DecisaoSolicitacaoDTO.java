package school.sptech.sistema_estoque.dto.estoque.solicitacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DecisaoSolicitacaoDTO(
        @NotNull(message = "A decisão deve ser informada (true para aceitar, false para recusar)")
        @Schema(description = "Identificador de aprovacao")
        Boolean aceita
) {

}
