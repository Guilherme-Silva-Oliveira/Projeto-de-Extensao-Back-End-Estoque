package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    // Buscas gerais
    Optional<Fornecedor> findByNome(String nome);
    Optional<Fornecedor> findByEmail(String email);
    Optional<Fornecedor> findByTelefone(String telefone);
    List<Fornecedor> findByTipoFornecedorId(Integer tipoFornecedorId);
    Optional<Fornecedor> findByNomeAndEmail(String nome, String email);

    // Verificação de duplicata antes de cadastrar
    Boolean existsByEmail(String email);
    Boolean existsByTelefone(String telefone);
    Boolean existsByEmailAndTelefone(String email, String telefone);

}
