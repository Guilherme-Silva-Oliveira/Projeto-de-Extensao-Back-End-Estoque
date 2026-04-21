package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.escala.EscalaRequest;
import school.sptech.sistema_estoque.dto.estoque.escala.EscalaResponse;
import school.sptech.sistema_estoque.dto.mapper.EscalaMapper;
import school.sptech.sistema_estoque.service.EscalaService;

import java.util.List;

@RestController
@RequestMapping("/v1/escalas")
public class EscalaController {
    private final EscalaService service;
    public EscalaController(EscalaService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar uma Escala")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Escala Cadastrada")
    })
    @PostMapping
    public ResponseEntity<EscalaResponse> cadastrarEscala(@RequestBody EscalaRequest request){
        var escala = service.cadastrarEscala(request);
        return ResponseEntity.status(201).body(EscalaMapper.toResponse(escala));
    }

    @Operation(summary = "Listar Todas as Escalas")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Escala Encontrada"),
            @ApiResponse(responseCode = "200",description = "Escalas Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<EscalaResponse>> listarEscalas(){
        var escalas = service.listarEscala();
        return ResponseEntity.ok(escalas.stream().map(EscalaMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Escala")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhuma Escala Encontrada"),
            @ApiResponse(responseCode = "204",description = "Escala Excluída")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEscala(@PathVariable Integer id){
        service.excluirEscala(id);
        return ResponseEntity.noContent().build();
    }
}
