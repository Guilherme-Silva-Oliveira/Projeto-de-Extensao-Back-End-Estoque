package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.service.AlmoxarifadoService;

import java.util.List;

@RestController
@RequestMapping("/v1/almoxarifados")
public class AlmoxarifadoController {
    private final AlmoxarifadoService service;
    public AlmoxarifadoController(AlmoxarifadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlmoxarifadoResponse> cadastrarAlmoxarifado(@RequestBody AlmoxarifadoRequest request){
        return ResponseEntity.ok(service.cadastrarAlmoxarifado(request));
    }

    @GetMapping
    public ResponseEntity<List<AlmoxarifadoResponse>> listarAlmoxarifados(@RequestBody AlmoxarifadoRequest request){
        return ResponseEntity.ok(service.listarAlmoxarifados());
    }
}
