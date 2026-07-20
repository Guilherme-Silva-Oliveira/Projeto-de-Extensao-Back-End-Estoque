package school.sptech.sistema_estoque.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
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
    Optional<Status> findStatusById(Integer id);
    List<Optional<Historico>> findBySolicitacaoId(Integer id);
    void salvarAlerta(AlertaDevolucao alerta);
}
