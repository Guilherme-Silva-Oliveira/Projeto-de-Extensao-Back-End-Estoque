package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteResponse;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteResponse;
import school.sptech.sistema_estoque.dto.mapper.LimiteMapper;
import school.sptech.sistema_estoque.service.LimiteService;

import java.util.List;

@RestController
@RequestMapping("/v1/limites")
public class LimiteController {
    private final LimiteService service;

    public LimiteController(LimiteService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Limite")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Tipo Limite Não Encontrado"),
            @ApiResponse(responseCode = "201",description = "Limite Cadastrado")
    })
    @PostMapping
    public ResponseEntity<LimiteResponse> cadastrarLimite(@RequestBody LimiteRequest request){
        return ResponseEntity.status(201).body(LimiteMapper.toLimiteResponse(service.cadastrarLimite(request)));
    }

    @Operation(summary = "Listar Todos os Limites")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Limite Encontrado"),
            @ApiResponse(responseCode = "200",description = "Limites Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<LimiteResponse>> listarLimites(){
        return ResponseEntity.ok(service.listarLimites().stream().map(LimiteMapper::toLimiteResponse).toList());
    }

    @Operation(summary = "Excluir Limite")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Limite Encontrado"),
            @ApiResponse(responseCode = "204",description = "Limite Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLimite(@PathVariable Integer id){
        service.excluirLimite(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cadastrar um Tipo Limite")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Tipo Limite Cadastrado")
    })
    @PostMapping("/tipos")
    public ResponseEntity<TipoLimiteResponse> cadastrarTipoLimite(@RequestBody TipoLimiteRequest request){
        return ResponseEntity.status(201).body(LimiteMapper.toTipoLimiteResponse(service.cadastrarTipoLimite(request)));
    }

    @Operation(summary = "Listar Todos os Tipo Limites")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Tipo Limite Encontrado"),
            @ApiResponse(responseCode = "200",description = "Tipo Limites Encontrados")
    })
    @GetMapping("/tipos")
    public ResponseEntity<List<TipoLimiteResponse>> listarTiposLimite(){
        return ResponseEntity.ok(service.listarTiposLimite().stream().map(LimiteMapper::toTipoLimiteResponse).toList());
    }

    @Operation(summary = "Excluir Tipo Limite")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Tipo Limite Encontrado"),
            @ApiResponse(responseCode = "204",description = "Tipo Limite Excluído")
    })
    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<Void> excluirTipoLimite(@PathVariable Integer id){
        service.excluirTipoLimite(id);
        return ResponseEntity.noContent().build();
    }
}
