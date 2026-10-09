package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.limite.LimitePatchDto;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteRequest;
import school.sptech.sistema_estoque.dto.estoque.limite.LimiteResponse;
import school.sptech.sistema_estoque.dto.mapper.LimiteMapper;
import school.sptech.sistema_estoque.service.LimiteService;

import java.util.List;

@RestController
@RequestMapping("/v1/limites")
@Tag(name = "Limites",description = "Operações Relacionadas à Limites")
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
        var limites = service.listarLimites();
        if (limites.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(limites.stream().map(LimiteMapper::toLimiteResponse).toList());
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

    @Operation(summary = "Atualizar Limite")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Limite não encontrado"),
            @ApiResponse(responseCode = "200", description = "Limite atualizado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<LimiteResponse> atualizarLimite(
            @PathVariable Integer id,
            @RequestBody LimitePatchDto dto
    ){
        return ResponseEntity.ok(LimiteMapper.toLimiteResponse(service.atualizarLimite(id, dto)));
    }
}
