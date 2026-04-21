package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoSaidaId;
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

    public SaidaService(PedidoSaidaRepository pedidoSaidaRepository, MaterialRepository materialRepository,
                        SolicitacaoRepository solicitacaoRepository, EscalaRepository escalaRepository,
                        List<MovimentacaoObserver> observers) {
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
        if (request == null){throw new EntidadeInvalidException("Pedido Saida Inválido");}
        Optional<Material> materialOptional = materialRepository.findById(request.materialId());
        if (materialOptional.isEmpty()) {throw new EntidadeInvalidException("Material não encontrado");}
        Material material = materialOptional.get();
        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(request.solicitacaoId());
        if (solicitacaoOptional.isEmpty()) {throw new EntidadeInvalidException("Solicitação de origem não encontrada");}
        Solicitacao solicitacao = solicitacaoOptional.get();
        Optional<Escala> escalaOptional = escalaRepository.findById(request.escalaId());
        if (escalaOptional.isEmpty()) {throw new EntidadeInvalidException("Escala associada não encontrada");}
        Escala escala = escalaOptional.get();

        material.setQuantidade(material.getQuantidade() - request.quantidade());
        materialRepository.save(material);

        PedidoSaida pedidoSaida = new PedidoSaida(material, solicitacao, request.quantidade(), request.dataSolicitacao(), escala);
        PedidoSaida saved = pedidoSaidaRepository.save(pedidoSaida);

        String mensagem = "Material " + material.getNomeMaterial() + " saiu com quantidade " + request.quantidade();
        observers.forEach(observer -> {
            observer.gerarLogs(mensagem);
            observer.atualizar(mensagem);
        });
        return saved;
    }

    public void excluirPedidoSaida(Integer materialId, Integer solicitacaoId){
        PedidoSaidaId id = new PedidoSaidaId();
        id.setMaterial(materialId);
        id.setSolicitacao(solicitacaoId);
        Optional<PedidoSaida> opt = pedidoSaidaRepository.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Saída não encontrada");}
        pedidoSaidaRepository.delete(opt.get());
    }
}
