package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.CategoriaRequest;
import school.sptech.sistema_estoque.dto.estoque.CategoriaResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    private final CategoriaService service;
    private final SistemaMapper mapper;
    public CategoriaController(CategoriaService service, SistemaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> cadastrarCategoria(@RequestBody CategoriaRequest request){
        var categoria = service.cadastrarCategoria(request);
        return ResponseEntity.ok(mapper.toCategoriaResponse(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        var categorias = service.listarCategorias();
        return ResponseEntity.ok(categorias.stream().map(mapper::toCategoriaResponse).toList());
    }
}
