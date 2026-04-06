package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.estoque.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.dto.estoque.UnidadeMedidaResponse;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.service.SolicitacaoService;
import school.sptech.sistema_estoque.service.UnidadeMedidaService;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class UnidadeMedidaController {
    private final UnidadeMedidaService service;
    public UnidadeMedidaController(UnidadeMedidaService service) {
        this.service = service;
    }

    public ResponseEntity<UnidadeMedidaResponse> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaRequest request){
        return ResponseEntity.ok(service.cadastrarUnidadeMedida(request));
    }
    public ResponseEntity<List<UnidadeMedidaResponse>> listarSaidas(){
        return ResponseEntity.ok(service.listarUnidadeMedida());
    }
}
