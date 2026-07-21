package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;

import java.util.List;
import java.util.Optional;

public interface ListaMaterialRepository extends JpaRepository<ListaMaterial, Integer> {
    List<Optional<ListaMaterial>> findAllBySolicitacaoId(Integer solicitacaoId);
}
