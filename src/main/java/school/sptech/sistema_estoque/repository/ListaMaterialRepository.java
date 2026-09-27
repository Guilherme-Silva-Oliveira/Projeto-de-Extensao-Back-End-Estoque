package school.sptech.sistema_estoque.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.sistema_estoque.dto.estoque.dashboard.MaterialMaisSolicitadoDto;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ListaMaterialRepository extends JpaRepository<ListaMaterial, Integer> {
    List<Optional<ListaMaterial>> findAllBySolicitacaoId(Integer solicitacaoId);

    @Query("""
        SELECT new school.sptech.sistema_estoque.dto.estoque.dashboard.MaterialMaisSolicitadoDto(
            lm.material.nomeMaterial,
            SUM(CAST(lm.quantidade AS long))
        )
        FROM ListaMaterial lm
        WHERE lm.solicitacao.dataSolicitacao BETWEEN :dataInicio AND :dataFim
        GROUP BY lm.material.nomeMaterial
        ORDER BY SUM(lm.quantidade) DESC
    """)
    List<MaterialMaisSolicitadoDto> findMaterialMaisSolicitadoPorPeriodo(
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            Pageable pageable
    );

    @Query(value = """
    SELECT m.nome_material AS nomeMaterial, COALESCE(SUM(lm.quantidade), 0) AS total
    FROM lista_material lm
    JOIN material m ON m.id = lm.material_id
    JOIN solicitacao s ON s.id = lm.solicitacao_id
    WHERE NOT EXISTS (
        SELECT 1 FROM historico h
        WHERE h.solicitacao_id = s.id
        AND h.status_solicitacao IN ('REJEITADA', 'CANCELADA', 'PRAZO_EXPIRADO')
        AND h.data_alteracao = (
            SELECT MAX(h2.data_alteracao) FROM historico h2 WHERE h2.solicitacao_id = s.id
        )
    )
    GROUP BY m.nome_material
""", nativeQuery = true)
    List<MaterialQuantidadeRepository> somarSaidasPorMaterial();

}