package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.pedido_entrada.PedidoEntradaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.CodigoBarras;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoEntradaId;
import school.sptech.sistema_estoque.observer.MovimentacaoObserver;
import school.sptech.sistema_estoque.repository.CodigoBarrasRepository;
import school.sptech.sistema_estoque.repository.FornecedorRepository;
import school.sptech.sistema_estoque.repository.MaterialRepository;
import school.sptech.sistema_estoque.repository.PedidoEntradaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {
    private final FornecedorRepository fornecedorRepository;
    private final MaterialRepository materialRepository;
    private final CodigoBarrasRepository codigoBarrasRepository;
    private final PedidoEntradaRepository pedidoEntradaRepository;
    private final List<MovimentacaoObserver> observers;

    public EntradaService(FornecedorRepository fornecedorRepository, MaterialRepository materialRepository,
                          CodigoBarrasRepository codigoBarrasRepository, PedidoEntradaRepository pedidoEntradaRepository,
                          List<MovimentacaoObserver> observers) {
        this.fornecedorRepository = fornecedorRepository;
        this.materialRepository = materialRepository;
        this.codigoBarrasRepository = codigoBarrasRepository;
        this.pedidoEntradaRepository = pedidoEntradaRepository;
        this.observers = observers;
    }

    public PedidoEntrada cadastrarPedidoEntrada(PedidoEntradaRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Pedido entrada invalido");}

        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(request.fornecedorId());
        if (fornecedorOptional.isEmpty()) {throw new EntidadeInvalidException("Fornecedor nao encontrado");}
        Optional<Material> materialOptional = materialRepository.findById(request.materialId());
        if (materialOptional.isEmpty()) {throw new EntidadeInvalidException("Material nao encontrado");}
        Optional<CodigoBarras> codigoOptional = codigoBarrasRepository.findById(request.codigo());
        if (codigoOptional.isEmpty()) {throw new EntidadeInvalidException("Codigo de barras nao encontrado");}

        Material material = materialOptional.get();
        if (!codigoOptional.get().getMaterial().getId().equals(material.getId())) {
            throw new EntidadeInvalidException("Codigo de barras nao pertence ao material informado");
        }

        material.setQuantidade(material.getQuantidade() + request.quantidade());
        materialRepository.save(material);

        PedidoEntrada pedidoEntrada = new PedidoEntrada(fornecedorOptional.get(), material, request.quantidade(), request.dataEntrada());
        PedidoEntrada saved = pedidoEntradaRepository.save(pedidoEntrada);

        String mensagem = "Material " + material.getNomeMaterial() + " entrou com quantidade " + request.quantidade();
        observers.forEach(observer -> {
            observer.gerarLogs(mensagem);
            observer.atualizar(mensagem);
        });

        return saved;
    }

    public List<PedidoEntrada> listarPedidosEntrada() {
        return pedidoEntradaRepository.findAll();
    }

    public void excluirEntrada(Integer fornecedorId, Integer materialId){
        PedidoEntradaId id = new PedidoEntradaId();
        id.setFornecedor(fornecedorId);
        id.setMaterial(materialId);
        Optional<PedidoEntrada> opt = pedidoEntradaRepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Entrada não encontrada");}
        pedidoEntradaRepository.delete(opt.get());
    }
}
