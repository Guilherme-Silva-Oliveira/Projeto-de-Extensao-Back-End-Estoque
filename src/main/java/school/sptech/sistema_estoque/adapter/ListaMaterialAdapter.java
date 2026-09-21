package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.ListaMaterialPort;
import school.sptech.sistema_estoque.port.SolicitacaoPort;
import school.sptech.sistema_estoque.repository.*;

import java.util.List;
import java.util.Optional;

@Component
public class ListaMaterialAdapter implements ListaMaterialPort {
    private final ListaMaterialRepository repository;
    public ListaMaterialAdapter(ListaMaterialRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListaMaterial save(ListaMaterial listaMaterial) {
        return repository.save(listaMaterial);
    }

    @Override
    public Optional<ListaMaterial> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<ListaMaterial> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Optional<ListaMaterial>> findBySolicitacaoId(Integer solicitacaoId) {
        return repository.findAllBySolicitacaoId(solicitacaoId);
    }
}
