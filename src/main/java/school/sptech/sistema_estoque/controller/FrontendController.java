package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.movimentacao.MovimentacaoFront;
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
    public ResponseEntity<List<MovimentacaoFront>> listarMovimentacoes(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        var movimentacoes = service.listarMovimentacoes(pageable);
        if (movimentacoes.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(movimentacoes);
    }
}
