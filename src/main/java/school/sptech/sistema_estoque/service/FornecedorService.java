package school.sptech.sistema_estoque.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorPatchDto;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorRequest;
import school.sptech.sistema_estoque.exception.*;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;
import school.sptech.sistema_estoque.port.FornecedorPort;
import school.sptech.sistema_estoque.port.TipoFornecedorPort;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private final FornecedorPort fornecedorPort;
    private final TipoFornecedorPort tipoFornecedorPort;
    public FornecedorService(FornecedorPort fornecedorPort , TipoFornecedorPort tipoFornecedorPort) {
        this.fornecedorPort = fornecedorPort;
        this.tipoFornecedorPort = tipoFornecedorPort;
    }

    public TipoFornecedor cadastrarTipoFornecedor(TipoFornecedorRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Tipo fornecedor invalido");}
        TipoFornecedor tipoFornecedor = new TipoFornecedor(null, request.nomeTipo());
        return tipoFornecedorPort.save(tipoFornecedor);
    }

    public List<TipoFornecedor> listarTipoFornecedores() {
        return tipoFornecedorPort.findAll();
    }

    public void excluirTipoFornecedor(Integer id){
        Optional<TipoFornecedor> opt = tipoFornecedorPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Tipo Fornecedor Não Encontrado");}
        tipoFornecedorPort.delete(opt.get());
    }

    public Fornecedor cadastrarFornecedor(FornecedorRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Fornecedor invalido");}
        if (fornecedorPort.existsByEmailAndTelefone(request.email(), request.telefone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um almoxarife cadastrado com esse email e id de almoxarifado");
        }
        Optional<TipoFornecedor> tipoOptional = tipoFornecedorPort.findById(request.idTipoFornecedor());
        if (tipoOptional.isEmpty()) {throw new EntidadeInvalidException("Tipo fornecedor nao encontrado");}
        Fornecedor fornecedor = new Fornecedor(null, request.nome(), request.email(), request.telefone(), tipoOptional.get());
        return fornecedorPort.save(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedorPort.findAll();
    }

    public void excluirFornecedor(Integer id){
        Optional<Fornecedor> opt = fornecedorPort.findById(id);
        if (opt.isEmpty()){throw new EntidadeNaoExisteException("Fornecedor Não Encontrado");}
        fornecedorPort.delete(opt.get());
    }

    public Fornecedor atualizarFornecedor(Integer id, FornecedorPatchDto dto){

        Optional<Fornecedor> fornecedorOptional = fornecedorPort.findById(id);

        if (fornecedorOptional.isEmpty()){
            throw new EntidadeNaoExisteException("Fornecedor Não Encontrado");
        }

        if (dto == null){
            throw new EntidadeInvalidException("Dados inválidos");
        }

        Fornecedor fornecedor = fornecedorOptional.get();

        if (dto.nome() != null){
            fornecedor.setNome(dto.nome());
        }

        if (dto.email() != null){
            fornecedor.setEmail(dto.email());
        }

        if (dto.telefone() != null){
            fornecedor.setTelefone(dto.telefone());
        }

        if (dto.tipoFornecedor() != null && dto.tipoFornecedor().id() != null){

            Optional<TipoFornecedor> tipoOptional =
                    tipoFornecedorPort.findById(dto.tipoFornecedor().id());

            if (tipoOptional.isEmpty()){
                throw new EntidadeInvalidException("Tipo fornecedor nao encontrado");
            }

            fornecedor.setTipoFornecedor(tipoOptional.get());
        }

        return fornecedorPort.save(fornecedor);
    }
}
