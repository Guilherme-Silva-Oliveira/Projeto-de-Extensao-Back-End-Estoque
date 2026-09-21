package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Historico;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.port.HistoricoPort;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;
import school.sptech.sistema_estoque.repository.HistoricoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class HistoricoAdapter implements HistoricoPort {
    private final HistoricoRepository repository;

    public HistoricoAdapter(HistoricoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Historico save(Historico historico) {
        return repository.save(historico);
    }

    @Override
    public List<Historico> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Optional<Historico>> findBySolicitacaoId(Integer solicitacaoId) {
        return repository.findBySolicitacaoId(solicitacaoId);
    }

    @Override
    public Optional<Historico> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Historico historico) {
        repository.delete(historico);
    }

}
