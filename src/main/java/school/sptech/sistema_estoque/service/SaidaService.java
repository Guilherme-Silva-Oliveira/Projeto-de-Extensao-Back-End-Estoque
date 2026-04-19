package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaRequest;
import school.sptech.sistema_estoque.exception.*;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.observer.MovimentacaoObserver;
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
    private final List<MovimentacaoObserver> observers;
    public SaidaService(PedidoSaidaRepository pedidoSaidaRepository, MaterialRepository materialRepository, SolicitacaoRepository solicitacaoRepository, EscalaRepository escalaRepository, List<MovimentacaoObserver> observers) {
        this.pedidoSaidaRepository = pedidoSaidaRepository;
        this.materialRepository = materialRepository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.escalaRepository = escalaRepository;
        this.observers = observers;
    }

    public List<PedidoSaida> listarPedidoSaida() {
        return pedidoSaidaRepository.findAll();
    }

    public PedidoSaida cadastrarPedidoSaida(PedidoSaidaRequest request) {
        if (request == null){throw new EntidadeInvalidException("Pedido Saida Inváldo");}
        Optional<Material> materialOptional = materialRepository.findById(request.materialId());
        if (materialOptional.isEmpty()) {throw new EntidadeInvalidException("Material não encontrado");}
        Material material = materialOptional.get();
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(request.solicitacaoId());
        if (solicitacaoOptional.isEmpty()) {throw new EntidadeInvalidException("Solicitação de origem não encontrada");}
        Solicitacao solicitacao = solicitacaoOptional.get();
        Optional<Escala> escalaOptional = escalaRepository.findById(request.escalaId());
        if (escalaOptional.isEmpty()) {throw new EntidadeInvalidException("Escala associada não encontrada");}
        Escala escala = escalaOptional.get();
        PedidoSaida pedidoSaida = new PedidoSaida(material, solicitacao, request.quantidade(), request.dataSolicitacao(), escala);
        PedidoSaida saved = pedidoSaidaRepository.save(pedidoSaida);

        // Notificar observers
        String mensagem = "Material " + material.getNomeMaterial() + " saiu com quantidade " + request.quantidade();
        observers.forEach(observer -> {
            observer.gerarLogs(mensagem);
            observer.atualizar(mensagem);
        });
        return saved;
    }

    // ARRUMAR
    public void excluirPedidoSaida(Integer id){
        Optional<PedidoSaida> opt = pedidoSaidaRepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Almoxarifado Não Encontrado");}
        pedidoSaidaRepository.delete(opt.get());
    }
}