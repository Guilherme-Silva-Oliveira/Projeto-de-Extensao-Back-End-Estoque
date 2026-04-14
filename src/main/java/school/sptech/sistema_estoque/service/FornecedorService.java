package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.TipoFornecedorRequest;
import school.sptech.sistema_estoque.exception.InvalidFornecedorRequestException;
import school.sptech.sistema_estoque.exception.InvalidTipoFornecedorRequestException;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;
import school.sptech.sistema_estoque.repository.FornecedorRepository;
import school.sptech.sistema_estoque.repository.TipoFornecedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorRepository forrepository;
    private final TipoFornecedorRepository tpfrepository;
    public FornecedorService(FornecedorRepository forrepository, TipoFornecedorRepository tpfrepository) {
        this.forrepository = forrepository;
        this.tpfrepository = tpfrepository;
    }

    public TipoFornecedor cadastrarTipoFornecedor(TipoFornecedorRequest request) {
        if (request == null) {
            throw new InvalidTipoFornecedorRequestException("Tipo fornecedor invalido");
        }

        TipoFornecedor tipoFornecedor = new TipoFornecedor(null, request.nomeTipo());
        return tpfrepository.save(tipoFornecedor);
    }

    public List<TipoFornecedor> listarTipoFornecedores() {
        return tpfrepository.findAll();
    }

    public Fornecedor cadastrarFornecedor(FornecedorRequest request) {
        if (request == null) {
            throw new InvalidFornecedorRequestException("Fornecedor invalido");
        }

        Optional<TipoFornecedor> tipoOptional = tpfrepository.findById(request.idTipoFornecedor());
        if (tipoOptional.isEmpty()) {
            throw new InvalidTipoFornecedorRequestException("Tipo fornecedor nao encontrado");
        }

        Fornecedor fornecedor = new Fornecedor(null, request.nome(), request.email(), request.telefone(), request.cnpjCpf(), tipoOptional.get());
        return forrepository.save(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return forrepository.findAll();
    }
}
