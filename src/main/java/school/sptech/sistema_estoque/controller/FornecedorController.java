package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorResponse;
import school.sptech.sistema_estoque.service.FornecedorService;

import java.util.List;

@RestController
@RequestMapping("/v1/fornecedores")
public class FornecedorController {
    private final FornecedorService service;
    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Almoxarife")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Tipo Fornecedor Não Encontrado"),
            @ApiResponse(responseCode = "201",description = "Fornecedor Cadastrado")
    })
    @PostMapping
    public ResponseEntity<FornecedorResponse> cadastrarFornecedor(@RequestBody FornecedorRequest request){
        return ResponseEntity.status(201).body(service.cadastrarFornecedor(request));
    }

    @Operation(summary = "Listar Todos os Fornecedores")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Fornecedor Encontrado"),
            @ApiResponse(responseCode = "200",description = "Fornecedores Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<FornecedorResponse>> listarFornecedores(){
        return ResponseEntity.ok(service.listarFornecedores());
    }
}
