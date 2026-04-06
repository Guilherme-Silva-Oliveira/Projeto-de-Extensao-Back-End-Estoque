package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.codigo.CodigoRequest;
import school.sptech.sistema_estoque.dto.estoque.EscalaRequest;
import school.sptech.sistema_estoque.dto.estoque.EscalaResponse;
import school.sptech.sistema_estoque.dto.estoque.PedidoEntradaRequest;
import school.sptech.sistema_estoque.dto.estoque.PedidoEntradaResponse;
import school.sptech.sistema_estoque.service.EntradaService;
import school.sptech.sistema_estoque.service.EscalaService;

import java.util.List;

@RestController
@RequestMapping("/escalas")
public class EscalaController {
    private final EscalaService service;
    public EscalaController(EscalaService service) {
        this.service = service;
    }

    public ResponseEntity<EscalaResponse> cadastrarEscala(@RequestBody EscalaRequest request){
        return ResponseEntity.ok(service.cadastrarEscala(request));
    }
    public ResponseEntity<List<EscalaResponse>> listarCategorias(){
        return ResponseEntity.ok(service.listarEscala());
    }
}
