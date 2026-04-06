package school.sptech.sistema_estoque.dto.estoque;

import java.time.LocalDateTime;

public record PedidoEntradaRequest(
        Integer fornecedorId,
        Integer materialId,
        Integer quantidade,
        LocalDateTime dataEntrada
) {
}
