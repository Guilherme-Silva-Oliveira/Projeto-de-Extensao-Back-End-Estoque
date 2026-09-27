
package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.AlertaSolicitacao;

import java.util.List;
import java.util.Optional;

public interface AlertaSolicitacaoPort {
    List<AlertaSolicitacao> findAll();
    Optional<AlertaSolicitacao> findById(Integer id);
    void saveAlerta(AlertaSolicitacao solicitacao);
}
