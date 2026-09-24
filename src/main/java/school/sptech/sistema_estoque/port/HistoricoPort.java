package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Historico;
import school.sptech.sistema_estoque.model.estoque.Limite;

import java.util.List;
import java.util.Optional;

public interface HistoricoPort {
    Historico save(Historico historico);
    List<Historico> findAll();
    Optional<Historico> findById(Integer id);
    List<Optional<Historico>> findBySolicitacaoId(Integer solicitacaoId);
    void delete(Historico historico);
}
