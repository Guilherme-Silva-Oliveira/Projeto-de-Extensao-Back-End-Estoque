package school.sptech.sistema_estoque.dto.estoque.pedido_saida;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PedidoSaidaRequest(
     @NotNull @Schema(description = "Fk para Material",example = "1") Integer materialId,
     @NotNull @Schema(description = "Fk para Solicitação",example = "1") Integer solicitacaoId,
     @NotNull @Schema(description = "Quantidade Solicitada",example = "50") Integer quantidade,
     @NotNull @Schema(description = "Data da Solicitação",example = "20-04-2026") LocalDateTime dataSolicitacao,
     @NotNull @Schema(description = "Fk para Escala") Integer escalaId
) {
}
