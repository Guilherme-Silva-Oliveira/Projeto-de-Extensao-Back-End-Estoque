package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.pedido_entrada.PedidoEntradaRequest;
import school.sptech.sistema_estoque.dto.estoque.pedido_entrada.PedidoEntradaResponse;
import school.sptech.sistema_estoque.dto.mapper.EntradaMapper;
import school.sptech.sistema_estoque.service.EntradaService;

import java.util.List;

@RestController
@RequestMapping("/v1/entradas")
public class EntradaController {
    private final EntradaService service;

    public EntradaController(EntradaService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar uma Entrada")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Fornecedor Não Encontrado"),
            @ApiResponse(responseCode = "400",description = "Material Não Encontrado"),
            @ApiResponse(responseCode = "201",description = "Entrada Cadastrada")
    })
    @PostMapping
    public ResponseEntity<PedidoEntradaResponse> cadastrarEntrada(@RequestBody PedidoEntradaRequest request){
        return ResponseEntity.status(201).body(EntradaMapper.toResponse(service.cadastrarPedidoEntrada(request)));
    }

    @Operation(summary = "Listar Todas as Entradas")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Entrada Encontrada"),
            @ApiResponse(responseCode = "200",description = "Entradas Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<PedidoEntradaResponse>> listarEntradas(){
        return ResponseEntity.ok(service.listarPedidosEntrada().stream().map(EntradaMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Entrada")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhuma Entrada Encontrada"),
            @ApiResponse(responseCode = "204",description = "Entrada Excluída")
    })
    @DeleteMapping("/fornecedor/{fornecedorId}/material/{materialId}")
    public ResponseEntity<Void> excluirEntrada(@PathVariable Integer fornecedorId, @PathVariable Integer materialId){
        service.excluirEntrada(fornecedorId, materialId);
        return ResponseEntity.noContent().build();
    }
}
