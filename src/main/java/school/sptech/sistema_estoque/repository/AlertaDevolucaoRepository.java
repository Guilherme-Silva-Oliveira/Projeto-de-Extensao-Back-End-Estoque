package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;

import java.util.List;
import java.util.Optional;

public interface AlertaDevolucaoRepository extends JpaRepository<AlertaDevolucao, Integer> {
    List<Optional<AlertaDevolucao>> findAllBySolicitacaoId(Integer solicitacaoId);
}
