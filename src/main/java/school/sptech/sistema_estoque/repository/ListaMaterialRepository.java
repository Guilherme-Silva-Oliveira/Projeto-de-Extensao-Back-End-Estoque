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
}
