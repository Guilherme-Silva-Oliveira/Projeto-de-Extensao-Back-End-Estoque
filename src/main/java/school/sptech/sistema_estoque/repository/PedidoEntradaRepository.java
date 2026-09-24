package school.sptech.sistema_estoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;

public interface PedidoEntradaRepository extends JpaRepository<PedidoEntrada, Integer> {

    Page<PedidoEntrada> findByIsDevolucaoTrue(Pageable pageable);

}
