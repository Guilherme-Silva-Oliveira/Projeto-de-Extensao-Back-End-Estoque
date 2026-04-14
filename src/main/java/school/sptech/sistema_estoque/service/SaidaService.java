package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.PedidoSaidaRequest;
import school.sptech.sistema_estoque.exception.InvalidEscalaRequestException;
import school.sptech.sistema_estoque.exception.InvalidMaterialRequestException;
import school.sptech.sistema_estoque.exception.InvalidPedidoSaidaRequestException;
import school.sptech.sistema_estoque.exception.InvalidSolicitacaoRequestException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.repository.EscalaRepository;
import school.sptech.sistema_estoque.repository.MaterialRepository;
import school.sptech.sistema_estoque.repository.PedidoSaidaRepository;
import school.sptech.sistema_estoque.repository.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaService {
    private final PedidoSaidaRepository pedidoSaidaRepository;
    private final MaterialRepository materialRepository;
    private final SolicitacaoRepository solicitacaoRepository;
    private final EscalaRepository escalaRepository;
    public SaidaService(PedidoSaidaRepository pedidoSaidaRepository, MaterialRepository materialRepository, SolicitacaoRepository solicitacaoRepository, EscalaRepository escalaRepository) {
        this.pedidoSaidaRepository = pedidoSaidaRepository;
        this.materialRepository = materialRepository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.escalaRepository = escalaRepository;
    }

    public List<PedidoSaida> listarPedidoSaida() {
        return pedidoSaidaRepository.findAll();
    }

    public PedidoSaida cadastrarPedidoSaida(PedidoSaidaRequest request) {
        if (request == null){throw new InvalidPedidoSaidaRequestException("Pedido Saida Inváldo");}
        Optional<Material> materialOptional = materialRepository.findById(request.materialId());
        if (materialOptional.isEmpty()) {throw new InvalidMaterialRequestException("Material não encontrado");}
        Material material = materialOptional.get();

        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(request.solicitacaoId());
        if (solicitacaoOptional.isEmpty()) {throw new InvalidSolicitacaoRequestException("Solicitação de origem não encontrada");}
        Solicitacao solicitacao = solicitacaoOptional.get();

        Optional<Escala> escalaOptional = escalaRepository.findById(request.escalaId());
        if (escalaOptional.isEmpty()) {throw new InvalidEscalaRequestException("Escala associada não encontrada");}
        Escala escala = escalaOptional.get();

        PedidoSaida pedidoSaida = new PedidoSaida(material, solicitacao, request.quantidade(), request.dataSolicitacao(), escala, request.dataSaida());
        return pedidoSaidaRepository.save(pedidoSaida);
    }
}
