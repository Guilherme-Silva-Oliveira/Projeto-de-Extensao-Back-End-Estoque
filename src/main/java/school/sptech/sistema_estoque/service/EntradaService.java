package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.codigo.CodigoRequest;
import school.sptech.sistema_estoque.dto.estoque.PedidoEntradaRequest;
import school.sptech.sistema_estoque.dto.estoque.PedidoEntradaResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.InvalidFornecedorRequestException;
import school.sptech.sistema_estoque.exception.InvalidMaterialRequestException;
import school.sptech.sistema_estoque.exception.InvalidPedidoEntradaRequestException;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.repository.FornecedorRepository;
import school.sptech.sistema_estoque.repository.MaterialRepository;
import school.sptech.sistema_estoque.repository.PedidoEntradaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {
    private final FornecedorRepository fornecedorRepository;
    private final MaterialRepository materialRepository;
    private final PedidoEntradaRepository pedidoEntradaRepository;
    private final SistemaMapper mapper;
    public EntradaService(FornecedorRepository fornecedorRepository, MaterialRepository materialRepository, PedidoEntradaRepository pedidoEntradaRepository, SistemaMapper mapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.materialRepository = materialRepository;
        this.pedidoEntradaRepository = pedidoEntradaRepository;
        this.mapper = mapper;
    }

    public PedidoEntradaResponse cadastrarPedidoEntrada(PedidoEntradaRequest request, CodigoRequest codigo) {
        if (request == null) {throw new InvalidPedidoEntradaRequestException("Pedido entrada invalido");}

        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(request.fornecedorId());
        if (fornecedorOptional.isEmpty()) {throw new InvalidFornecedorRequestException("Fornecedor nao encontrado");}
        Optional<Material> materialOptional = materialRepository.findByCodigoBarras(codigo.codigo());
        if (materialOptional.isEmpty()) {throw new InvalidMaterialRequestException("Material nao encontrado");}
        PedidoEntrada pedidoEntrada = mapper.toPedidoEntradaEntity(request, fornecedorOptional.get(), materialOptional.get());
        PedidoEntrada salvo = pedidoEntradaRepository.save(pedidoEntrada);
        return mapper.toPedidoEntradaResponse(salvo);
    }

    public List<PedidoEntradaResponse> listarPedidosEntrada() {
        return pedidoEntradaRepository.findAll().stream()
                .map(mapper::toPedidoEntradaResponse)
                .toList();
    }
}
