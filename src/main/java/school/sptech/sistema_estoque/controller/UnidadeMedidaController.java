package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaResponse;
import school.sptech.sistema_estoque.dto.mapper.UnidadeMedidaMapper;
import school.sptech.sistema_estoque.service.UnidadeMedidaService;

import java.util.List;

@RestController
@RequestMapping("/v1/unidademedida")
@Tag(name = "Unidade de Medida",description = "Operações Relacionadas à Unidade de Medida")
public class UnidadeMedidaController {
    private final UnidadeMedidaService service;
    public UnidadeMedidaController(UnidadeMedidaService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Unidade de Medida")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Unidade de Medida Cadastrada")
    })
    @PostMapping
    public ResponseEntity<UnidadeMedidaResponse> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaRequest request){
        var unidade = service.cadastrarUnidadeMedida(request);
        return ResponseEntity.status(201).body(UnidadeMedidaMapper.toResponse(unidade));
    }

    @Operation(summary = "Listar Todas as Unidades de Medida")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Unidade de Medida Encontrada"),
            @ApiResponse(responseCode = "200",description = "Unidades de Medida Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<UnidadeMedidaResponse>> listarUnidadeMedida(){
        var unidades = service.listarUnidadeMedida();
        if (unidades.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(unidades.stream().map(UnidadeMedidaMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Unidade de Medida")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhuma Unidade de Medida Encontrada"),
            @ApiResponse(responseCode = "204",description = "Unidade de Medida Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUnidadeMedida(@PathVariable Integer id){
        service.excluirUnidadeMedida(id);
        return ResponseEntity.noContent().build();
    }
}
