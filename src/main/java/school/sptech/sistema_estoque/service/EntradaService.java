package school.sptech.sistema_estoque.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import school.sptech.sistema_estoque.dto.estoque.pedido_entrada.PedidoEntradaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.CodigoBarras;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoEntradaId;
import school.sptech.sistema_estoque.observer.MovimentacaoObserver;
import school.sptech.sistema_estoque.port.CodigoBarrasPort;
import school.sptech.sistema_estoque.port.FornecedorPort;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.port.PedidoEntradaPort;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {
    private final FornecedorPort fornecedorPort;
    private final MaterialPort materialPort;
    private final CodigoBarrasPort codigoBarrasPort;
    private final PedidoEntradaPort pedidoEntradaPort;
    private final MovimentacaoObserver observer;

    public EntradaService(FornecedorPort fornecedorPort, MaterialPort materialPort, CodigoBarrasPort codigoBarrasPort, PedidoEntradaPort pedidoEntradaPort,
            @Qualifier("logEntrada") MovimentacaoObserver observer) { 
        this.fornecedorPort = fornecedorPort;
        this.materialPort = materialPort;
        this.codigoBarrasPort = codigoBarrasPort;
        this.pedidoEntradaPort = pedidoEntradaPort;
        this.observer = observer;
    }

    public PedidoEntrada cadastrarPedidoEntrada(PedidoEntradaRequest request) {
        if (request == null) { throw new EntidadeInvalidException("Pedido entrada invalido"); }
        Optional<Fornecedor> fornecedorOptional = fornecedorPort.findById(request.fornecedorId());
        if (fornecedorOptional.isEmpty()) { throw new EntidadeInvalidException("Fornecedor nao encontrado"); }
        Optional<Material> materialOptional = materialPort.findById(request.materialId());
        if (materialOptional.isEmpty()) { throw new EntidadeInvalidException("Material nao encontrado"); }
        Optional<CodigoBarras> codigoOptional = codigoBarrasPort.findById(request.codigo());
        if (codigoOptional.isEmpty()) { throw new EntidadeInvalidException("Codigo de barras nao encontrado"); }

        Material material = materialOptional.get();
        if (!codigoOptional.get().getMaterial().getId().equals(material.getId())) {
            throw new EntidadeInvalidException("Codigo de barras nao pertence ao material informado");
        }

        material.setQuantidade(material.getQuantidade() + request.quantidade());
        materialPort.save(material);

        PedidoEntrada pedidoEntrada = new PedidoEntrada(fornecedorOptional.get(), material, request.quantidade(), request.dataEntrada(), request.isDevolucao());
        PedidoEntrada saved = pedidoEntradaPort.save(pedidoEntrada);

        String mensagem = "Material '" + material.getNomeMaterial() + "' entrou com quantidade " + request.quantidade();
        observer.gerarLogs(mensagem);
        observer.atualizar(mensagem);
        return saved;
    }

    public List<PedidoEntrada> listarPedidosEntrada() {
        return pedidoEntradaPort.findAll();
    }

    public void excluirEntrada(Integer fornecedorId, Integer materialId) {
        PedidoEntradaId id = new PedidoEntradaId();
        id.setFornecedor(fornecedorId);
        id.setMaterial(materialId);
        Optional<PedidoEntrada> opt = pedidoEntradaPort.findById(id);
        if (opt.isEmpty()) { throw new EntidadeNaoExisteException("Entrada não encontrada"); }
        pedidoEntradaPort.delete(opt.get());
    }

    @Transactional
    public PedidoEntrada definirDevolucao(Integer fornecedorId, Integer materialId){
        PedidoEntradaId id = new PedidoEntradaId();
        id.setFornecedor(fornecedorId);
        id.setMaterial(materialId);
        Optional<PedidoEntrada> pedidoEntrada = pedidoEntradaPort.findById(id);
        if (pedidoEntrada.isEmpty()){
            throw new EntidadeInvalidException("PedidoEntrada nao encontrado");
        }
        pedidoEntrada.get().setDevolucao(true);
        PedidoEntrada saved = pedidoEntradaPort.save(pedidoEntrada.get());

        String mensagem = "PedidoEntrada com fornecedor: '" + pedidoEntrada.get().getFornecedor().getId() + "' e material '" + pedidoEntrada.get().getMaterial().getId() +
                 "' da data: '" + pedidoEntrada.get().getDataEntrada() + "' foi definido como devolucao.";

        observer.gerarLogs(mensagem);
        observer.atualizar(mensagem);
        return saved;
    }

    public Page<PedidoEntrada> listarPedidosEntradaDevolucao(Pageable pageable) {
        return pedidoEntradaPort.buscarApenasDevolucoes(pageable);
    }
}