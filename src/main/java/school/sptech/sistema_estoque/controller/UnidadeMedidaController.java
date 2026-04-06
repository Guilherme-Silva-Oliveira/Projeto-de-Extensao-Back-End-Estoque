package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaResponse;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.service.SolicitacaoService;
import school.sptech.sistema_estoque.service.UnidadeMedidaService;

import java.util.List;

@RestController
@RequestMapping("/v1/unidademedida")
public class UnidadeMedidaController {
    private final UnidadeMedidaService service;
    public UnidadeMedidaController(UnidadeMedidaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UnidadeMedidaResponse> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaRequest request){
        return ResponseEntity.ok(service.cadastrarUnidadeMedida(request));
    }

    @GetMapping
    public ResponseEntity<List<UnidadeMedidaResponse>> listarSaidas(){
        return ResponseEntity.ok(service.listarUnidadeMedida());
    }
}
