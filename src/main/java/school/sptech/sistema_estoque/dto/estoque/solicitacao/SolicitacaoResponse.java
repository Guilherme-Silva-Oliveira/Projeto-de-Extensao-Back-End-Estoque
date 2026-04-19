package school.sptech.sistema_estoque.dto.estoque.solicitacao;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.model.estoque.Professor;

import java.time.LocalDateTime;

public record SolicitacaoResponse(
        @Schema(description = "ID da Solicitação") Integer id,
        @Schema(description = "Professor Associado") Professor professor,
        @Schema(description = "Motivo da Solicitação")String motivo,
        @Schema(description = "Data da Solicitação") LocalDateTime dataSolicitacao
) {
}
