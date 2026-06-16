package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class FornecedorService {
    private final FornecedorPort fornecedorPort;
    private final TipoFornecedorPort tipoFornecedorPort;

    public TipoFornecedor cadastrarTipoFornecedor(TipoFornecedorRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Tipo fornecedor invalido");}
        TipoFornecedor tipoFornecedor = new TipoFornecedor(null, request.nomeTipo());
        return tipoFornecedorPort.save(tipoFornecedor);
    }

    public List<TipoFornecedor> listarTipoFornecedores() {
        return tipoFornecedorPort.findAll();
    }

    public void excluirTipoFornecedor(Integer id){
        TipoFornecedor tipoFornecedor = tipoFornecedorPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Tipo Fornecedor Não Encontrado"));
        tipoFornecedorPort.delete(tipoFornecedor);
    }

    public Fornecedor cadastrarFornecedor(FornecedorRequest request) {
        if (request == null) {throw new EntidadeInvalidException("Fornecedor invalido");}
        if (fornecedorPort.existsByEmailAndTelefone(request.email(), request.telefone())){throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um almoxarife cadastrado com esse email e id de almoxarifado");}
        TipoFornecedor tipoFornecedor = tipoFornecedorPort.findById(request.idTipoFornecedor()).orElseThrow(()-> new EntidadeNaoExisteException("Tipo Fornecedor Não Encontrado"));
        Fornecedor fornecedor = new Fornecedor(); fornecedor.setNome(request.nome()); fornecedor.setEmail(request.email()); fornecedor.setTelefone(request.telefone()); fornecedor.setTipoFornecedor(tipoFornecedor);
        return fornecedorPort.save(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedorPort.findAll();
    }

    public void excluirFornecedor(Integer id){
        Fornecedor fornecedor = fornecedorPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Fornecedor Não Encontrado"));
        fornecedorPort.delete(fornecedor);
    }

    public Fornecedor atualizarFornecedor(Integer id, FornecedorPatchDto dto){
        if (dto == null){throw new EntidadeInvalidException("Dados inválidos");}
        Fornecedor fornecedor = fornecedorPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Fornecedor Não Encontrado"));
        if (dto.nome() != null){fornecedor.setNome(dto.nome());}
        if (dto.email() != null){fornecedor.setEmail(dto.email());}
        if (dto.telefone() != null){fornecedor.setTelefone(dto.telefone());}
        if (dto.tipoFornecedor() != null && dto.tipoFornecedor().id() != null){
            TipoFornecedor tipoFornecedor = tipoFornecedorPort.findById(dto.tipoFornecedor().id()).orElseThrow(()-> new EntidadeNaoExisteException("Tipo Fornecedor Não Encontrado"));
            fornecedor.setTipoFornecedor(tipoFornecedor);
        }
        return fornecedorPort.save(fornecedor);
    }
}
