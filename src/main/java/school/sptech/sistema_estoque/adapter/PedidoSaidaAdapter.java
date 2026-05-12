package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoSaidaId;
import school.sptech.sistema_estoque.port.PedidoSaidaPort;
import school.sptech.sistema_estoque.repository.PedidoSaidaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoSaidaAdapter implements PedidoSaidaPort {
    private final PedidoSaidaRepository pedidoSaidaRepository;

    public PedidoSaidaAdapter(PedidoSaidaRepository pedidoSaidaRepository) {
        this.pedidoSaidaRepository = pedidoSaidaRepository;
    }

    @Override
    public PedidoSaida save(PedidoSaida pedidoSaida) {
        return pedidoSaidaRepository.save(pedidoSaida);
    }

    @Override
    public List<PedidoSaida> findAll() {
        return pedidoSaidaRepository.findAll();
    }

    @Override
    public Optional<PedidoSaida> findById(PedidoSaidaId id) {
        return pedidoSaidaRepository.findById(id);
    }

    @Override
    public void delete(PedidoSaida pedidoSaida) {
        pedidoSaidaRepository.delete(pedidoSaida);
    }
}
