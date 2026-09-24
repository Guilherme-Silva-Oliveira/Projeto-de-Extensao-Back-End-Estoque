package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.port.AlertaDevolucaoPort;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.repository.AlertaDevolucaoRepository;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AlertaDevolucaoAdapter implements AlertaDevolucaoPort {
    private final AlertaDevolucaoRepository repository;

    public AlertaDevolucaoAdapter(AlertaDevolucaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AlertaDevolucao> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AlertaDevolucao> findById(Integer id) {
        return repository.findById(id);
    }
}
