package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.FornecedorResponse;
import school.sptech.sistema_estoque.dto.estoque.TipoFornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.TipoFornecedorResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
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
    private final SistemaMapper mapper;
    public FornecedorService(FornecedorRepository forrepository, TipoFornecedorRepository tpfrepository, SistemaMapper mapper) {
        this.forrepository = forrepository;
        this.tpfrepository = tpfrepository;
        this.mapper = mapper;
    }

    public TipoFornecedorResponse cadastrarTipoFornecedor(TipoFornecedorRequest request) {
        if (request == null) {
            throw new InvalidTipoFornecedorRequestException("Tipo fornecedor invalido");
        }

        TipoFornecedor tipoFornecedor = mapper.toTipoFornecedorEntity(request);
        TipoFornecedor salvo = tpfrepository.save(tipoFornecedor);
        return mapper.toTipoFornecedorResponse(salvo);
    }

    public List<TipoFornecedorResponse> listarTipoFornecedores() {
        return tpfrepository.findAll().stream()
                .map(mapper::toTipoFornecedorResponse)
                .toList();
    }

    public FornecedorResponse cadastrarFornecedor(FornecedorRequest request) {
        if (request == null) {
            throw new InvalidFornecedorRequestException("Fornecedor invalido");
        }

        Optional<TipoFornecedor> tipoOptional = tpfrepository.findById(request.idTipoFornecedor());
        if (tipoOptional.isEmpty()) {
            throw new InvalidTipoFornecedorRequestException("Tipo fornecedor nao encontrado");
        }

        Fornecedor fornecedor = mapper.toFornecedorEntity(request, tipoOptional.get());
        Fornecedor salvo = forrepository.save(fornecedor);
        return mapper.toFornecedorResponse(salvo);
    }

    public List<FornecedorResponse> listarFornecedores() {
        return forrepository.findAll().stream()
                .map(mapper::toFornecedorResponse)
                .toList();
    }
}
