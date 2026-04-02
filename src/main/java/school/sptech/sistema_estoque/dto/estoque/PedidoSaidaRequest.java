package school.sptech.sistema_estoque.dto.estoque;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PedidoSaidaRequest(
     Integer materialId,
     Integer solicitacaoId,
     Integer quantidade,
     Integer escalaId,
     LocalDateTime dataSaida
) {
}
