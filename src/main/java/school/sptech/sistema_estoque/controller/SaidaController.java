package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaRequest;
import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaResponse;
import school.sptech.sistema_estoque.service.SaidaService;

import java.util.List;

@RestController
@RequestMapping("/v1/saidas")
public class SaidaController {
    private final SaidaService service;
    public SaidaController(SaidaService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar uma Saída")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Material Não Encontrado"),
            @ApiResponse(responseCode = "400",description = "Solicitação Não Encontrada"),
            @ApiResponse(responseCode = "400",description = "Escala Não Encontrada"),
            @ApiResponse(responseCode = "201",description = "Almoxarife Cadastrado")
    })
    @PostMapping
    public ResponseEntity<PedidoSaidaResponse> cadastrarSaida(@RequestBody PedidoSaidaRequest request){
        return ResponseEntity.status(201).body(service.cadastrarPedidoSaida(request));
    }

    @Operation(summary = "Listar Todas as Saídas")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Saída Encontrada"),
            @ApiResponse(responseCode = "200",description = "Saídas Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<PedidoSaidaResponse>> listarSaidas(){
        return ResponseEntity.ok(service.listarPedidoSaida());
    }

    @Operation(summary = "Excluir Saida")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhuma Saída Encontrada"),
            @ApiResponse(responseCode = "204",description = "Saída Excluída")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirSaida(Integer id){
        service.excluirPedidoSaida(id);
        return ResponseEntity.noContent().build();
    }
}
