package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorResponse;
import school.sptech.sistema_estoque.dto.estoque.movimentacao.MovimentacaoFront;
import school.sptech.sistema_estoque.dto.estoque.pedido_entrada.PedidoEntradaResponse;
import school.sptech.sistema_estoque.dto.mapper.EntradaMapper;
import school.sptech.sistema_estoque.dto.mapper.FornecedorMapper;
import school.sptech.sistema_estoque.service.FrontendService;

import java.util.List;

@RestController
@RequestMapping("v1/frontend")
public class FrontendController {
    private final FrontendService service;

    public FrontendController(FrontendService service) {
        this.service = service;
    }

    @Operation(summary = "Listar Todas as Movimentações")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Movimentação Encontrada"),
            @ApiResponse(responseCode = "200",description = "Movimentações Encontradas")
    })
    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoFront>> listarMovimentacoes(){
        var movimentacoes = service.listarMovimentacoes();
        if (movimentacoes.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(movimentacoes);
    }
}
