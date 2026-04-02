package school.sptech.sistema_estoque.dto.estoque;

import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.time.LocalDateTime;

public record PedidoSaidaResponse(
        Material material,
        Solicitacao solicitacao,
        Integer quantidade,
        LocalDateTime dataSolicitacao,
        Escala escala,
        LocalDateTime dataSaida
) {
}
