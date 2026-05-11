package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.port.FornecedorPort;
import school.sptech.sistema_estoque.repository.FornecedorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class FornecedorAdapter implements FornecedorPort {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorAdapter(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Optional<Fornecedor> findById(Integer id) {
        return fornecedorRepository.findById(id);
    }

    @Override
    public Boolean existsByEmailAndTelefone(String email, String telefone) {
        return fornecedorRepository.existsByEmailAndTelefone(email, telefone);
    }

    @Override
    public void delete(Fornecedor fornecedor) {
        fornecedorRepository.delete(fornecedor);
    }
}
