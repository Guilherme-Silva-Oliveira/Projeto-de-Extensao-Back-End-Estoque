package school.sptech.sistema_estoque.dto.estoque.solicitacao;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record SolicitacaoResponse(
        @Schema(description = "ID da Solicitação") Integer id,
        @Schema(description = "Nome do Professor que fez a solicitação") String professor,
        @Schema(description = "Descrição da Solicitação") String descricao,
        @Schema(description = "Data da Solicitação") LocalDateTime dataSolicitacao,
        @Schema(description = "Data da Solicitação") LocalDateTime dataParaEnvio,
        @Schema(description = "Alerta") String alerta,
        @Schema(description = "Motivo da Solicitação") String motivo

) {
}