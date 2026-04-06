package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.estoque.PedidoSaidaRequest;
import school.sptech.sistema_estoque.dto.estoque.PedidoSaidaResponse;
import school.sptech.sistema_estoque.dto.estoque.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.estoque.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.service.SaidaService;
import school.sptech.sistema_estoque.service.SolicitacaoService;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
    private final SolicitacaoService service;
    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }

    public ResponseEntity<SolicitacaoResponse> cadastrarSolicitacao(@RequestBody SolicitacaoIARequest request){
        return ResponseEntity.ok(service.cadastrarSolicitacao(request));
    }
    public ResponseEntity<List<SolicitacaoResponse>> listarSaidas(){
        return ResponseEntity.ok(service.listarSolicitacao());
    }
}
