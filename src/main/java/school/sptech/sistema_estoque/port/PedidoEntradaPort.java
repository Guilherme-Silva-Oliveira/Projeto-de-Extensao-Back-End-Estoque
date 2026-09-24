
package school.sptech.sistema_estoque.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;

import java.util.List;
import java.util.Optional;

public interface PedidoEntradaPort {
    PedidoEntrada save(PedidoEntrada pedidoEntrada);
    List<PedidoEntrada> findAll();
    Optional<PedidoEntrada> findById(Integer id);
    void delete(PedidoEntrada pedidoEntrada);
    Page<PedidoEntrada> buscarApenasDevolucoes(Pageable pageable);

}

