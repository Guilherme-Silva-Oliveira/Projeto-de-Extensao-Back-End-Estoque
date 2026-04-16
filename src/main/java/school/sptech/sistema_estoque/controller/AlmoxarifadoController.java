package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.service.AlmoxarifadoService;

import java.util.List;

@RestController
@RequestMapping("/v1/almoxarifados")
public class AlmoxarifadoController {
    private final AlmoxarifadoService service;
    private final SistemaMapper mapper;
    public AlmoxarifadoController(AlmoxarifadoService service, SistemaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<AlmoxarifadoResponse> cadastrarAlmoxarifado(@RequestBody AlmoxarifadoRequest request){
        var almoxarifado = service.cadastrarAlmoxarifado(request);
        return ResponseEntity.ok(mapper.toAlmoxarifadoResponse(almoxarifado));
    }

    @GetMapping
    public ResponseEntity<List<AlmoxarifadoResponse>> listarAlmoxarifados(){
        var almoxarifados = service.listarAlmoxarifados();
        return ResponseEntity.ok(almoxarifados.stream().map(mapper::toAlmoxarifadoResponse).toList());
    }
}

