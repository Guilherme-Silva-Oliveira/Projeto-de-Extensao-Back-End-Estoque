package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.material.MaterialResponse;
import school.sptech.sistema_estoque.dto.mapper.MaterialMapper;
import school.sptech.sistema_estoque.service.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/v1/materiais")
public class MaterialController {
    private final MaterialService service;
    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Material")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Categoria Não Encontrada"),
            @ApiResponse(responseCode = "400",description = "Estoque Não Encontrado"),
            @ApiResponse(responseCode = "400",description = "Unidade Medida Não Encontrada"),
            @ApiResponse(responseCode = "201",description = "Material Cadastrado")
    })
    @PostMapping
    public ResponseEntity<MaterialResponse> cadastrarMaterial(@RequestBody MaterialRequest request){
        var material = service.cadastrarMaterial(request);
        return ResponseEntity.status(201).body(MaterialMapper.toResponse(material));
    }

    @Operation(summary = "Listar Todos os Materiais")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Material Encontrado"),
            @ApiResponse(responseCode = "200",description = "Materiais Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<MaterialResponse>> listarMateriais(){
        var materiais = service.listarMateriais();
        return ResponseEntity.ok(materiais.stream().map(MaterialMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Material")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Material Encontrado"),
            @ApiResponse(responseCode = "204",description = "Material Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMaterial(@PathVariable Integer id){
        service.excluirMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
