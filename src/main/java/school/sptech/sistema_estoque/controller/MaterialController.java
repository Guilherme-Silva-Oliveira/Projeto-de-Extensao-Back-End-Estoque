package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.FornecedorResponse;
import school.sptech.sistema_estoque.dto.estoque.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.MaterialResponse;
import school.sptech.sistema_estoque.service.FornecedorService;
import school.sptech.sistema_estoque.service.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/v1/materiais")
public class MaterialController {
    private final MaterialService service;
    public MaterialController(MaterialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> cadastrarMaterial(@RequestBody MaterialRequest request){
        return ResponseEntity.ok(service.cadastrarMaterial(request));
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> listarMateriais(){
        return ResponseEntity.ok(service.listarMateriais());
    }
}
