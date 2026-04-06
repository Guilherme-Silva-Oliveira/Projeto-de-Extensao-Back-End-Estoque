package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.codigo.CodigoRequest;
import school.sptech.sistema_estoque.dto.estoque.CategoriaRequest;
import school.sptech.sistema_estoque.dto.estoque.CategoriaResponse;
import school.sptech.sistema_estoque.dto.estoque.PedidoEntradaRequest;
import school.sptech.sistema_estoque.dto.estoque.PedidoEntradaResponse;
import school.sptech.sistema_estoque.service.CategoriaService;
import school.sptech.sistema_estoque.service.EntradaService;

import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradaController {
    private final EntradaService service;
    public EntradaController(EntradaService service) {
        this.service = service;
    }

    public ResponseEntity<PedidoEntradaResponse> cadastrarEntrada(@RequestBody PedidoEntradaRequest request, @RequestBody CodigoRequest codigo){
        return ResponseEntity.ok(service.cadastrarPedidoEntrada(request,codigo));
    }
    public ResponseEntity<List<PedidoEntradaResponse>> listarCategorias(){
        return ResponseEntity.ok(service.listarPedidosEntrada());
    }
}
