package school.sptech.sistema_estoque.dto.estoque.solicitacao;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.Motivo;
import school.sptech.sistema_estoque.model.estoque.Professor;

import java.time.LocalDateTime;

public record SolicitacaoResponse(
        @Schema(description = "ID da Solicitação") Integer id,
        @Schema(description = "Quantidade da Solicitação") Integer quantidade,
        @Schema(description = "Descrição da Solicitação")String descricao,
        @Schema(description = "Data da Solicitação") LocalDateTime dataSolicitacao,
        @Schema(description = "Data da Solicitação") LocalDateTime dataParaEnvio
) {
}
