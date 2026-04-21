package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaRequest;
import school.sptech.sistema_estoque.dto.estoque.categoria.CategoriaResponse;
import school.sptech.sistema_estoque.dto.mapper.CategoriaMapper;
import school.sptech.sistema_estoque.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar uma Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Categoria Cadastrada")
    })
    @PostMapping
    public ResponseEntity<CategoriaResponse> cadastrarCategoria(@RequestBody CategoriaRequest request){
        var categoria = service.cadastrarCategoria(request);
        return ResponseEntity.status(201).body(CategoriaMapper.toResponse(categoria));
    }

    @Operation(summary = "Listar Todas as Categorias")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Categoria Encontrada"),
            @ApiResponse(responseCode = "200",description = "Categorias Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        var categorias = service.listarCategorias();
        return ResponseEntity.ok(categorias.stream().map(CategoriaMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhuma Categoria Encontrada"),
            @ApiResponse(responseCode = "204",description = "Categoria Excluída")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Integer id){
        service.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
