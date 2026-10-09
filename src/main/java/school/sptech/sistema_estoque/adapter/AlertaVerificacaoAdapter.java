package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.dto.mq.AlertaVerificacao;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.port.AlertaDevolucaoPort;
import school.sptech.sistema_estoque.port.AlertaVerificacaoPort;
import school.sptech.sistema_estoque.repository.AlertaDevolucaoRepository;
import school.sptech.sistema_estoque.repository.AlertaVerificacaoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AlertaVerificacaoAdapter implements AlertaVerificacaoPort {
    private final AlertaVerificacaoRepository repository;

    public AlertaVerificacaoAdapter(AlertaVerificacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AlertaVerificacao> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AlertaVerificacao> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void saveAlerta(AlertaVerificacao alerta) {
        repository.save(alerta);
    }
}
