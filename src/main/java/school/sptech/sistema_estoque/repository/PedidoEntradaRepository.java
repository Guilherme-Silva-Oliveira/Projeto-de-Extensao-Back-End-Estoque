package school.sptech.sistema_estoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;

import java.util.List;

public interface PedidoEntradaRepository extends JpaRepository<PedidoEntrada, Integer> {

    Page<PedidoEntrada> findByIsDevolucaoTrue(Pageable pageable);

    @Query(value = """
        SELECT m.nome_material AS nomeMaterial, COALESCE(SUM(pe.quantidade), 0) AS total
        FROM pedido_entrada pe
        JOIN material m ON m.id = pe.material_id
        WHERE pe.is_devolucao = false
        GROUP BY m.nome_material
    """, nativeQuery = true)
    List<MaterialQuantidadeRepository> somarEntradasPorMaterial();

}