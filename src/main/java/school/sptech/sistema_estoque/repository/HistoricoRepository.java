package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Historico;

import java.util.Optional;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
    Optional<Historico> findBySolicitacaoId(Integer id);
}
