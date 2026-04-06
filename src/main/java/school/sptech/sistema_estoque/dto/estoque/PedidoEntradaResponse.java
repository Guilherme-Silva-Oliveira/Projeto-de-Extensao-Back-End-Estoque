package school.sptech.sistema_estoque.dto.estoque;

import java.time.LocalDateTime;

public record PedidoEntradaResponse(
        Integer fornecedorId,
        Integer materialId,
        Integer quantidade,
        LocalDateTime dataEntrada,
        FornecedorResponse fornecedor,
        MaterialResponse material
) {
}
