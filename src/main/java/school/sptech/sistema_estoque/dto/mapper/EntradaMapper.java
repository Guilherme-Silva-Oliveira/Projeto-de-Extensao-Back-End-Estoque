package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorResponse;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;
import school.sptech.sistema_estoque.dto.estoque.pedido_entrada.PedidoEntradaRequest;
import school.sptech.sistema_estoque.dto.estoque.pedido_entrada.PedidoEntradaResponse;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;

public class EntradaMapper {
    public static PedidoEntrada toEntity(PedidoEntradaRequest request, Fornecedor fornecedor, Material material) {
        PedidoEntrada p = new PedidoEntrada();
        p.setFornecedor(fornecedor);
        p.setMaterial(material);
        p.setQuantidade(request.quantidade());
        p.setDataEntrada(request.dataEntrada());
        return p;
    }

    public static PedidoEntradaResponse toResponse(PedidoEntrada entity) {
        FornecedorResponse fornecedorResponse = entity.getFornecedor() != null
                ? FornecedorMapper.toFornecedorResponse(entity.getFornecedor())
                : null;
        MaterialResponse materialResponse = entity.getMaterial() != null
                ? MaterialMapper.toResponse(entity.getMaterial())
                : null;
        return new PedidoEntradaResponse(
                entity.getQuantidade(),
                entity.getDataEntrada(),
                fornecedorResponse,
                materialResponse
        );
    }
}
