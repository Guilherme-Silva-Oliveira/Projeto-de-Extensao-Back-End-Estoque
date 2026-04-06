package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoEntradaId;

public interface PedidoEntradaRepository extends JpaRepository<PedidoEntrada, PedidoEntradaId> {
}
