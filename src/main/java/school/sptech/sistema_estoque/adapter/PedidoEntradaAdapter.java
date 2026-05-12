package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoEntradaId;
import school.sptech.sistema_estoque.port.PedidoEntradaPort;
import school.sptech.sistema_estoque.repository.PedidoEntradaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoEntradaAdapter implements PedidoEntradaPort {
    private final PedidoEntradaRepository repository;

    public PedidoEntradaAdapter(PedidoEntradaRepository repository) {
        this.repository = repository;
    }

    @Override
    public PedidoEntrada save(PedidoEntrada pedidoEntrada) {
        return repository.save(pedidoEntrada);
    }

    @Override
    public List<PedidoEntrada> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<PedidoEntrada> findById(PedidoEntradaId id) {
        return repository.findById(id);
    }

    @Override
    public void delete(PedidoEntrada pedidoEntrada) {
        repository.delete(pedidoEntrada);
    }

}


