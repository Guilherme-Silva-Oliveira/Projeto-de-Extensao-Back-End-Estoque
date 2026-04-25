package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorResponse;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorResponse;
import school.sptech.sistema_estoque.dto.mapper.FornecedorMapper;
import school.sptech.sistema_estoque.service.FornecedorService;

import java.util.List;

@RestController
@RequestMapping("/v1/fornecedores")
@Tag(name = "Fornecedores",description = "Operações Relacionadas à Fornecedores")
public class FornecedorController {
    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Fornecedor")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Tipo Fornecedor Não Encontrado"),
            @ApiResponse(responseCode = "201",description = "Fornecedor Cadastrado")
    })
    @PostMapping
    public ResponseEntity<FornecedorResponse> cadastrarFornecedor(@RequestBody FornecedorRequest request){
        return ResponseEntity.status(201).body(FornecedorMapper.toFornecedorResponse(service.cadastrarFornecedor(request)));
    }

    @Operation(summary = "Listar Todos os Fornecedores")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Fornecedor Encontrado"),
            @ApiResponse(responseCode = "200",description = "Fornecedores Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<FornecedorResponse>> listarFornecedores(){
        var fornecedores = service.listarFornecedores();
        if (fornecedores.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(fornecedores.stream().map(FornecedorMapper::toFornecedorResponse).toList());
    }

    @Operation(summary = "Excluir Fornecedor")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Fornecedor Encontrado"),
            @ApiResponse(responseCode = "204",description = "Fornecedor Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFornecedor(@PathVariable Integer id){
        service.excluirFornecedor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cadastrar um Tipo Fornecedor")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Tipo Fornecedor Cadastrado")
    })
    @PostMapping("/tipos")
    public ResponseEntity<TipoFornecedorResponse> cadastrarTipoFornecedor(@RequestBody TipoFornecedorRequest request){
        return ResponseEntity.status(201).body(FornecedorMapper.toTipoFornecedorResponse(service.cadastrarTipoFornecedor(request)));
    }

    @Operation(summary = "Listar Todos os Tipo Fornecedores")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Tipo Fornecedor Encontrado"),
            @ApiResponse(responseCode = "200",description = "Tipo Fornecedores Encontrados")
    })
    @GetMapping("/tipos")
    public ResponseEntity<List<TipoFornecedorResponse>> listarTipoFornecedores(){
        return ResponseEntity.ok(service.listarTipoFornecedores().stream().map(FornecedorMapper::toTipoFornecedorResponse).toList());
    }

    @Operation(summary = "Excluir Tipo Fornecedor")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Tipo Fornecedor Encontrado"),
            @ApiResponse(responseCode = "204",description = "Tipo Fornecedor Excluído")
    })
    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<Void> excluirTipoFornecedor(@PathVariable Integer id){
        service.excluirTipoFornecedor(id);
        return ResponseEntity.noContent().build();
    }
}
