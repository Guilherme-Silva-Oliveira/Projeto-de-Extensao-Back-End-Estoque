package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.SetorEstoque;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.port.SetorEstoquePort;
import school.sptech.sistema_estoque.repository.AlmoxarifadoRepository;
import school.sptech.sistema_estoque.repository.SetorEstoqueRepository;

import java.util.List;
import java.util.Optional;

@Component
public class SetorEstoqueAdapter implements SetorEstoquePort {
    private final SetorEstoqueRepository repository;

    public SetorEstoqueAdapter(SetorEstoqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<SetorEstoque> findByIdentificadorSetor(String identificador) {
        return repository.findByIdentificadorSetor(identificador);
    }
}
