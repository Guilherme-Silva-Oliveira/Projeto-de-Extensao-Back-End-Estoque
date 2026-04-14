package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.EscalaRequest;
import school.sptech.sistema_estoque.dto.estoque.EscalaResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.service.EscalaService;

import java.util.List;

@RestController
@RequestMapping("/v1/escalas")
public class EscalaController {
    private final EscalaService service;
    private final SistemaMapper mapper;
    public EscalaController(EscalaService service, SistemaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<EscalaResponse> cadastrarEscala(@RequestBody EscalaRequest request){
        var escala = service.cadastrarEscala(request);
        return ResponseEntity.ok(mapper.toEscalaResponse(escala));
    }

    @GetMapping
    public ResponseEntity<List<EscalaResponse>> listarCategorias(){
        var escalas = service.listarEscala();
        return ResponseEntity.ok(escalas.stream().map(mapper::toEscalaResponse).toList());
    }
}
