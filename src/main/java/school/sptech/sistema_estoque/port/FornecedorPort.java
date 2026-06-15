package school.sptech.sistema_estoque.port;

import java.util.List;
import java.util.Optional;

import school.sptech.sistema_estoque.model.estoque.Fornecedor;

public interface FornecedorPort {
    Fornecedor save(Fornecedor fornecedor);
    List<Fornecedor> findAll();
    Optional<Fornecedor> findById(Integer id);
    Boolean existsByEmailAndTelefone(String email, String telefone);
    void delete(Fornecedor fornecedor);
}
