package school.sptech.sistema_estoque.dto.estoque.pedido_entrada;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PedidoEntradaRequest(
        @NotNull @Schema(description = "Fk para Fornecedor",example = "1") Integer fornecedorId,
        @NotNull @Schema(description = "Fk para Material",example = "1") Integer materialId,
        @NotNull @Schema(description = "Quantidade do Material", example = "50") Integer quantidade,
        @NotNull @Schema(description = "Data de Entrada do Material",example = "17-09-2025") LocalDateTime dataEntrada
) {
}
