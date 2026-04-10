package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifeResponse;
import school.sptech.sistema_estoque.service.AlmoxarifeService;

import java.util.List;

@RestController
@RequestMapping("/v1/almoxarifes")
public class AlmoxarifeController {
    private final AlmoxarifeService service;
    public AlmoxarifeController(AlmoxarifeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlmoxarifeResponse> cadastrarAlmoxarife(@RequestBody AlmoxarifeRequest request){
        return ResponseEntity.ok(service.cadastrarAlmoxarife(request));
    }
    @GetMapping
    public ResponseEntity<List<AlmoxarifeResponse>> listarAlmoxarifes(){
        return ResponseEntity.ok(service.listarAlmoxarifes());
    }
}
