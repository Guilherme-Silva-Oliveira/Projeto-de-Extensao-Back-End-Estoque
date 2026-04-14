package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.MaterialResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.service.MaterialService;

import java.util.List;

@RestController
@RequestMapping("/v1/materiais")
public class MaterialController {
    private final MaterialService service;
    private final SistemaMapper mapper;
    public MaterialController(MaterialService service, SistemaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> cadastrarMaterial(@RequestBody MaterialRequest request){
        var material = service.cadastrarMaterial(request);
        return ResponseEntity.ok(mapper.toMaterialResponse(material));
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> listarMateriais(){
        var materiais = service.listarMateriais();
        return ResponseEntity.ok(materiais.stream().map(mapper::toMaterialResponse).toList());
    }
}
