package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.*;
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
@AllArgsConstructor
public class SaidaService {
    private final PedidoSaidaPort pedidoSaidaPort;
    private final MaterialPort materialPort;
    private final SolicitacaoPort solicitacaoPort;
    private final EscalaPort escalaPort;
    private final MovimentacaoObserver observer;

    public List<PedidoSaida> listarPedidoSaida() {
        List<PedidoSaida> pedidos = pedidoSaidaPort.findAll();
        if (pedidos.isEmpty()) {
            throw new EntidadeNaoExisteException("Nenhum pedido de saída encontrado");
        }
        return pedidos;
    }

    public PedidoSaida cadastrarPedidoSaida(PedidoSaidaRequest request) {
        if (request == null) { throw new EntidadeInvalidException("Pedido Saida Inválido"); }
        Material material = materialPort.findById(request.materialId()).orElseThrow(()-> new EntidadeNaoExisteException("Material Não Encontrado"));
        Solicitacao solicitacao = solicitacaoPort.findById(request.solicitacaoId()).orElseThrow(()-> new EntidadeNaoExisteException("Solicitação Não Encontrado"));
        Escala escala = escalaPort.findById(request.escalaId()).orElseThrow(()-> new EntidadeNaoExisteException("Escala Não Encontrado"));
        material.setQuantidade(material.getQuantidade() - request.quantidade());
        materialPort.save(material);
        PedidoSaida pedidoSaida = new PedidoSaida(material, solicitacao, request.quantidade(), request.dataSolicitacao(), escala, request.inteligenciaArtificialId());
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
        PedidoSaida pedidoSaida = pedidoSaidaPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Pedido de Saída Não Encontrado"));
        pedidoSaidaPort.delete(pedidoSaida);
    }
}