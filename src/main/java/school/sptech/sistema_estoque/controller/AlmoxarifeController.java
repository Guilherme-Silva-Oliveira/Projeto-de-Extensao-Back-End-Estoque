package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeResponse;
import school.sptech.sistema_estoque.service.AlmoxarifeService;

import java.util.List;

@RestController
@RequestMapping("/v1/almoxarifes")
public class AlmoxarifeController {
    private final AlmoxarifeService service;
    public AlmoxarifeController(AlmoxarifeService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Almoxarife")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Almoxarifado Não Encontrado"),
            @ApiResponse(responseCode = "201",description = "Almoxarife Cadastrado")
    })
    @PostMapping
    public ResponseEntity<AlmoxarifeResponse> cadastrarAlmoxarife(@RequestBody AlmoxarifeRequest request){
        return ResponseEntity.status(201).body(service.cadastrarAlmoxarife(request));
    }

    @Operation(summary = "Listar Todos os Almoxarifes")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Almoxarife Encontrado"),
            @ApiResponse(responseCode = "200",description = "Almoxarifes Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<AlmoxarifeResponse>> listarAlmoxarifes(){
        return ResponseEntity.ok(service.listarAlmoxarifes());
    }

    @Operation(summary = "Excluir Almoxarife")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Almoxarife Encontrado"),
            @ApiResponse(responseCode = "204",description = "Almoxarife Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAlmoxarife(Integer id){
        service.excluirAlmoxarife(id);
        return ResponseEntity.noContent().build();
    }
}
