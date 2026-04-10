package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.EscalaRequest;
import school.sptech.sistema_estoque.dto.estoque.EscalaResponse;
import school.sptech.sistema_estoque.dto.estoque.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.FornecedorResponse;
import school.sptech.sistema_estoque.service.EscalaService;
import school.sptech.sistema_estoque.service.FornecedorService;

import java.util.List;

@RestController
@RequestMapping("/v1/fornecedores")
public class FornecedorController {
    private final FornecedorService service;
    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FornecedorResponse> cadastrarFornecedor(@RequestBody FornecedorRequest request){
        return ResponseEntity.ok(service.cadastrarFornecedor(request));
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponse>> listarCategorias(){
        return ResponseEntity.ok(service.listarFornecedores());
    }
}
