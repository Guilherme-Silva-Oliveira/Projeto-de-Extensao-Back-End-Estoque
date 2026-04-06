package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.estoque.CategoriaRequest;
import school.sptech.sistema_estoque.dto.estoque.CategoriaResponse;
import school.sptech.sistema_estoque.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    public ResponseEntity<CategoriaResponse> cadastrarCategoria(@RequestBody CategoriaRequest request){
        return ResponseEntity.ok(service.cadastrarCategoria(request));
    }
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(service.listarCategorias());
    }
}
