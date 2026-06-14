package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoSaidaId;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PedidoSaidaPort {
    PedidoSaida save(PedidoSaida pedidoSaida);
    List<PedidoSaida> findAll();
    Optional<PedidoSaida> findById(PedidoSaidaId id);
    void delete(PedidoSaida pedidoSaida);
}
