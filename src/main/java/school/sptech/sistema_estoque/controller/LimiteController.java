package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.service.FornecedorService;
import school.sptech.sistema_estoque.service.LimiteService;

import java.util.List;

@RestController
@RequestMapping("/limites")
public class LimiteController {
    private final LimiteService service;
    public LimiteController(LimiteService service) {
        this.service = service;
    }

    public ResponseEntity<LimiteResponse> cadastrarLimite(@RequestBody LimiteRequest request){
        return ResponseEntity.ok(service.cadastrarLimite(request));
    }
    public ResponseEntity<List<LimiteResponse>> listarLimites(){
        return ResponseEntity.ok(service.listarLimites());
    }
    public ResponseEntity<TipoLimiteResponse> cadastrarTipoLimite(@RequestBody TipoLimiteRequest request){
        return ResponseEntity.ok(service.cadastrarTipoLimite(request));
    }
    public ResponseEntity<List<TipoLimiteResponse>> listarTiposLimite(){
        return ResponseEntity.ok(service.listarTiposLimite());
    }
}
