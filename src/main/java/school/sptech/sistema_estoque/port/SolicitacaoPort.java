package school.sptech.sistema_estoque.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.sptech.sistema_estoque.model.estoque.*;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoPort {
    Solicitacao save(Solicitacao solicitacao);
    Optional<Solicitacao> findByProfessorId(Integer professorId);
    List<Solicitacao> findAll();
    Optional<Solicitacao> findById(Integer id);
    void delete(Solicitacao solicitacao);
    Historico saveHistorico (Historico historico);
    Optional<Status> findStatusById(Integer id);
    List<Optional<Historico>> findBySolicitacaoId(Integer id);
    void salvarAlerta(AlertaDevolucao alerta);
    void salvarLista(ListaMaterial lista);
    List<Optional<AlertaDevolucao>> findAlertaBySolicitacaoId(Integer solicitacaoId);
    List<Optional<ListaMaterial>> findListaBySolicitacaoId(Integer solicitacaoId);
}
