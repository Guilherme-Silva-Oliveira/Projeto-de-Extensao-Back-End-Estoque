package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaResponse;
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

    @Operation(summary = "Cadastrar um Unidade de Medida")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Unidade de Medida Cadastrada")
    })
    @PostMapping
    public ResponseEntity<UnidadeMedidaResponse> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaRequest request){
        var unidade = service.cadastrarUnidadeMedida(request);
        return ResponseEntity.status(201).body(mapper.toUnidadeMedidaResponse(unidade));
    }

    @Operation(summary = "Listar Todas as Unidades de Medida")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Unidade de Medida Encontrada"),
            @ApiResponse(responseCode = "200",description = "Unidades de Medida Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<UnidadeMedidaResponse>> listarSaidas(){
        var unidades = service.listarUnidadeMedida();
        return ResponseEntity.ok(unidades.stream().map(mapper::toUnidadeMedidaResponse).toList());
    }
}
