package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoSaidaId;

public interface PedidoSaidaRepository extends JpaRepository<PedidoSaida, PedidoSaidaId> {
}
