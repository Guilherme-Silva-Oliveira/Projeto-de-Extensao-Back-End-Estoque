package school.sptech.sistema_estoque.dto.estoque.pedido_entrada;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorResponse;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;

import java.time.LocalDateTime;

public record PedidoEntradaResponse(
        @Schema(description = "Quantidade da Entrada")Integer quantidade,
        @Schema(description = "Data da Entrada do Material") LocalDateTime dataEntrada,
        @Schema(description = "Fornecedor Associado") FornecedorResponse fornecedor,
        @Schema(description = "Material Associado") MaterialResponse material
) {
}
