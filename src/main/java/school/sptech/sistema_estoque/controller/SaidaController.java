package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.PedidoSaidaRequest;
import school.sptech.sistema_estoque.dto.estoque.PedidoSaidaResponse;
import school.sptech.sistema_estoque.dto.estoque.ProfessorRequest;
import school.sptech.sistema_estoque.dto.estoque.ProfessorResponse;
import school.sptech.sistema_estoque.service.ProfessorService;
import school.sptech.sistema_estoque.service.SaidaService;

import java.util.List;

@RestController
@RequestMapping("/v1/saidas")
public class SaidaController {
    private final SaidaService service;
    public SaidaController(SaidaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PedidoSaidaResponse> cadastrarSaida(@RequestBody PedidoSaidaRequest request){
        return ResponseEntity.ok(service.cadastrarPedidoSaida(request));
    }

    @GetMapping
    public ResponseEntity<List<PedidoSaidaResponse>> listarSaidas(){
        return ResponseEntity.ok(service.listarPedidoSaida());
    }
}
