package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.service.UnidadeMedidaService;

import java.util.List;

@RestController
@RequestMapping("/v1/unidademedida")
public class UnidadeMedidaController {
    private final UnidadeMedidaService service;
    private final SistemaMapper mapper;
    public UnidadeMedidaController(UnidadeMedidaService service, SistemaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UnidadeMedidaResponse> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaRequest request){
        var unidade = service.cadastrarUnidadeMedida(request);
        return ResponseEntity.ok(mapper.toUnidadeMedidaResponse(unidade));
    }

    @GetMapping
    public ResponseEntity<List<UnidadeMedidaResponse>> listarSaidas(){
        var unidades = service.listarUnidadeMedida();
        return ResponseEntity.ok(unidades.stream().map(mapper::toUnidadeMedidaResponse).toList());
    }
}
