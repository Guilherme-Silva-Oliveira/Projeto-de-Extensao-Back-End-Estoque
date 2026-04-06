package school.sptech.sistema_estoque.dto.estoque;

import java.time.LocalDateTime;

public record PedidoSaidaRequest(
     Integer materialId,
     Integer solicitacaoId,
     Integer quantidade,
     LocalDateTime dataSolicitacao,
     Integer escalaId,
     LocalDateTime dataSaida
) {
}
