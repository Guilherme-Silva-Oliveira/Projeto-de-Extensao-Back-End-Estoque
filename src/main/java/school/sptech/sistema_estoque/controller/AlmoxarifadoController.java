package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.mapper.AlmoxarifadoMapper;
import school.sptech.sistema_estoque.service.AlmoxarifadoService;

import java.util.List;

@RestController
@RequestMapping("/v1/almoxarifados")
public class AlmoxarifadoController {
    private final AlmoxarifadoService service;
    public AlmoxarifadoController(AlmoxarifadoService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Almoxarifado")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Almoxarifado Cadastrado")
    })
    @PostMapping
    public ResponseEntity<AlmoxarifadoResponse> cadastrarAlmoxarifado(@RequestBody AlmoxarifadoRequest request){
        var almoxarifado = service.cadastrarAlmoxarifado(request);
        return ResponseEntity.status(201).body(AlmoxarifadoMapper.toResponse(almoxarifado));
    }

    @Operation(summary = "Listar Todos os Almoxarifados")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Almoxarifado Encontrado"),
            @ApiResponse(responseCode = "200",description = "Almoxarifados Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<AlmoxarifadoResponse>> listarAlmoxarifados(){
        var almoxarifados = service.listarAlmoxarifados();
        return ResponseEntity.ok(almoxarifados.stream().map(AlmoxarifadoMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Almoxarifado")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Almoxarifado Encontrado"),
            @ApiResponse(responseCode = "204",description = "Almoxarifado Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAlmoxarifado(@PathVariable Integer id){
        service.excluirAlmoxarifado(id);
        return ResponseEntity.noContent().build();
    }
}

