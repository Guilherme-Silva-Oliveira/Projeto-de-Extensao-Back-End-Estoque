package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaRequest;
import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaResponse;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

public class SaidaMapper {
    public static PedidoSaida toEntity(PedidoSaidaRequest request, Material material, Solicitacao solicitacao, Escala escala) {
        PedidoSaida entity = new PedidoSaida();
        entity.setMaterial(material);
        entity.setSolicitacao(solicitacao);
        entity.setEscala(escala);
        entity.setQuantidade(request.quantidade());
        entity.setDataSolicitacao(request.dataSolicitacao() != null ? request.dataSolicitacao() : java.time.LocalDateTime.now());
        return entity;
    }

    public static PedidoSaidaResponse toResponse(PedidoSaida entity) {
        return new PedidoSaidaResponse(
                entity.getMaterial(),
                entity.getSolicitacao(),
                entity.getQuantidade(),
                entity.getDataSolicitacao(),
                entity.getEscala());
    }
}
