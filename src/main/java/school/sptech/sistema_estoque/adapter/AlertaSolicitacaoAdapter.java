package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.AlertaSolicitacao;
import school.sptech.sistema_estoque.port.AlertaDevolucaoPort;
import school.sptech.sistema_estoque.port.AlertaSolicitacaoPort;
import school.sptech.sistema_estoque.repository.AlertaDevolucaoRepository;
import school.sptech.sistema_estoque.repository.AlertaSolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AlertaSolicitacaoAdapter implements AlertaSolicitacaoPort {
    private final AlertaSolicitacaoRepository repository;

    public AlertaSolicitacaoAdapter(AlertaSolicitacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<AlertaSolicitacao> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<AlertaSolicitacao> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveAlerta(AlertaSolicitacao solicitacao) {
        repository.save(solicitacao);
    }
}
