package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.movimentacao.MovimentacaoFront;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

public class MovimentacaoMapper {

    public static MovimentacaoFront fromSolicitacaotoEntity(ListaMaterial material) {
        return new MovimentacaoFront(
            "Saída",
            material.getMaterial().getNomeMaterial(),
            material.getSolicitacao().getMotivo().getDescricao(),
            material.getSolicitacao().getDataSolicitacao(),
            material.getQuantidade()
        );
    }

    public static MovimentacaoFront fromEntradaEntity(PedidoEntrada entrada) {
        String acao = "";
        if (entrada.isDevolucao()){
            acao = "Devolução";
        }else {
            acao = "Entrada";
        }
        return new MovimentacaoFront(
            acao,
            entrada.getMaterial().getNomeMaterial(),
            entrada.getFornecedor().getNome(),
            entrada.getDataEntrada(),
            entrada.getQuantidade()
        );
    }
}
