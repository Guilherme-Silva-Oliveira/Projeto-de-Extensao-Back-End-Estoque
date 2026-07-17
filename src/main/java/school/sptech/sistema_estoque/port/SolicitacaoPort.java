package school.sptech.sistema_estoque.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.sistema_estoque.model.estoque.Historico;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.model.estoque.Status;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoPort {
    Solicitacao save(Solicitacao solicitacao);
    List<Solicitacao> findAll();
    Optional<Solicitacao> findById(Integer id);
    void delete(Solicitacao solicitacao);
    Historico saveHistorico (Historico historico);
    List<Optional<Historico>> findHistoricoById(Integer id);
    Optional<Status> findStatusById(Integer id);
    Optional<Historico> findBySolicitacaoId(Integer id);
}
