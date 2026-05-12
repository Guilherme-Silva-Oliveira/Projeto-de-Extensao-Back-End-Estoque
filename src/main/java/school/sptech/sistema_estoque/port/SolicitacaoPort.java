package school.sptech.sistema_estoque.port;

import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoPort {

    Solicitacao save(Solicitacao solicitacao);

    List<Solicitacao> findAll();

    Optional<Solicitacao> findById(Integer id);

    void delete(Solicitacao solicitacao);
}
