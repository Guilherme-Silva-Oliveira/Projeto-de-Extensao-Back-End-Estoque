package school.sptech.sistema_estoque.dto.estoque.pedido_saida;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.time.LocalDateTime;

public record PedidoSaidaResponse(
        @Schema(description = "Material Associado") Material material,
        @Schema(description = "Solicitação Associada") Solicitacao solicitacao,
        @Schema(description = "Quantidade da Solicitação") Integer quantidade,
        @Schema(description = "Data da Solicitação") LocalDateTime dataSolicitacao,
        @Schema(description = "Escala Associada") Escala escala
) {
}
