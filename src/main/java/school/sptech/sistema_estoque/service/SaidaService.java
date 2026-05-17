package school.sptech.sistema_estoque.service;

import org.springframework.beans.factory.annotation.Qualifier;
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
import school.sptech.sistema_estoque.port.EscalaPort;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.port.PedidoSaidaPort;
import school.sptech.sistema_estoque.port.SolicitacaoPort;
import school.sptech.sistema_estoque.repository.EscalaRepository;
import school.sptech.sistema_estoque.repository.MaterialRepository;
import school.sptech.sistema_estoque.repository.PedidoSaidaRepository;
import school.sptech.sistema_estoque.repository.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaService {
    private final PedidoSaidaPort pedidoSaidaPort;
    private final MaterialPort materialPort;
    private final SolicitacaoPort solicitacaoPort;
    private final EscalaPort escalaPort;
    private final MovimentacaoObserver observer;

    public SaidaService(PedidoSaidaPort pedidoSaidaPort, MaterialPort materialPort, SolicitacaoPort solicitacaoPort, EscalaPort escalaPort, @Qualifier("logSaida") MovimentacaoObserver observer) {
        this.pedidoSaidaPort = pedidoSaidaPort;
        this.materialPort = materialPort;
        this.solicitacaoPort = solicitacaoPort;
        this.escalaPort = escalaPort;
        this.observer = observer;
    }

    public List<PedidoSaida> listarPedidoSaida() {
        List<PedidoSaida> pedidos = pedidoSaidaPort.findAll();
        if (pedidos.isEmpty()) {
            throw new EntidadeNaoExisteException("Nenhum pedido de saída encontrado");
        }
        return pedidos;
    }

    public PedidoSaida cadastrarPedidoSaida(PedidoSaidaRequest request) {
        if (request == null) { throw new EntidadeInvalidException("Pedido Saida Inválido"); }
        Optional<Material> materialOptional = materialPort.findById(request.materialId());
        if (materialOptional.isEmpty()) { throw new EntidadeInvalidException("Material não encontrado"); }
        Material material = materialOptional.get();
        Optional<Solicitacao> solicitacaoOptional = solicitacaoPort.findById(request.solicitacaoId());
        if (solicitacaoOptional.isEmpty()) { throw new EntidadeInvalidException("Solicitação de origem não encontrada"); }
        Solicitacao solicitacao = solicitacaoOptional.get();
        Optional<Escala> escalaOptional = escalaPort.findById(request.escalaId());
        if (escalaOptional.isEmpty()) { throw new EntidadeInvalidException("Escala associada não encontrada"); }
        Escala escala = escalaOptional.get();

        material.setQuantidade(material.getQuantidade() - request.quantidade());
        materialPort.save(material);

        PedidoSaida pedidoSaida = new PedidoSaida(material, solicitacao, request.quantidade(), request.dataSolicitacao(), escala);
        PedidoSaida saved = pedidoSaidaPort.save(pedidoSaida);

        String mensagem = "Material '" + material.getNomeMaterial() + "' saiu com quantidade " + request.quantidade();
        observer.gerarLogs(mensagem);
        observer.atualizar(mensagem);

        return saved;
    }

    public void excluirPedidoSaida(Integer materialId, Integer solicitacaoId) {
        PedidoSaidaId id = new PedidoSaidaId();
        id.setMaterial(materialId);
        id.setSolicitacao(solicitacaoId);
        Optional<PedidoSaida> opt = pedidoSaidaPort.findById(id);
        if (opt.isEmpty()) { throw new EntidadeNaoExisteException("Saída não encontrada"); }
        pedidoSaidaPort.delete(opt.get());
    }
}