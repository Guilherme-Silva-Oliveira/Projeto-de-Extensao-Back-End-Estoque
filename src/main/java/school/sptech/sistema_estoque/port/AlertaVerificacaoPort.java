
package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.dto.mq.AlertaVerificacao;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;

import java.util.List;
import java.util.Optional;

public interface AlertaVerificacaoPort {
    List<AlertaVerificacao> findAll();
    Optional<AlertaVerificacao> findById(Integer id);
    void saveAlerta(AlertaVerificacao alerta);
}
