package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.CodigoBarras;
import school.sptech.sistema_estoque.port.CodigoBarrasPort;
import school.sptech.sistema_estoque.repository.CodigoBarrasRepository;

import java.util.Optional;

@Component
public class CodigoBarrasAdapter implements CodigoBarrasPort {
    private final CodigoBarrasRepository codigoBarrasRepository;

    public CodigoBarrasAdapter(CodigoBarrasRepository codigoBarrasRepository) {
        this.codigoBarrasRepository = codigoBarrasRepository;
    }

    @Override
    public CodigoBarras save(CodigoBarras codigoBarras) {
        return codigoBarrasRepository.save(codigoBarras);
    }

    @Override
    public Optional<CodigoBarras> findById(String id) {
        return codigoBarrasRepository.findById(id);
    }
}
