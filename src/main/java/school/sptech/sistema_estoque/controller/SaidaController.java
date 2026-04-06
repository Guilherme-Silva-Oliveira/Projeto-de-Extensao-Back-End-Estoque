package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.estoque.PedidoSaidaRequest;
import school.sptech.sistema_estoque.dto.estoque.PedidoSaidaResponse;
import school.sptech.sistema_estoque.dto.estoque.ProfessorRequest;
import school.sptech.sistema_estoque.dto.estoque.ProfessorResponse;
import school.sptech.sistema_estoque.service.ProfessorService;
import school.sptech.sistema_estoque.service.SaidaService;

import java.util.List;

@RestController
@RequestMapping("/saidas")
public class SaidaController {
    private final SaidaService service;
    public SaidaController(SaidaService service) {
        this.service = service;
    }

    public ResponseEntity<PedidoSaidaResponse> cadastrarSaida(@RequestBody PedidoSaidaRequest request){
        return ResponseEntity.ok(service.cadastrarPedidoSaida(request));
    }
    public ResponseEntity<List<PedidoSaidaResponse>> listarSaidas(){
        return ResponseEntity.ok(service.listarPedidoSaida());
    }
}
