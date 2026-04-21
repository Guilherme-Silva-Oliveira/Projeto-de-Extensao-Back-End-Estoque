package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.service.SolicitacaoService;

import java.util.List;

@RestController
@RequestMapping("/v1/solicitacoes")
public class SolicitacaoController {
    private final SolicitacaoService service;
    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar uma Solicitação")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "400",description = "Professor Não Encontrado"),
            @ApiResponse(responseCode = "201",description = "Solicitação Cadastrada")
    })
    @PostMapping
    public ResponseEntity<SolicitacaoResponse> cadastrarSolicitacao(@RequestBody SolicitacaoIARequest request){
        return ResponseEntity.status(201).body(SolicitacaoMapper.toResponse(service.cadastrarSolicitacao(request)));
    }

    @Operation(summary = "Listar Todas as Solicitações")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Solicitação Encontrada"),
            @ApiResponse(responseCode = "200",description = "Solicitações Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<SolicitacaoResponse>> listarSolicitacoes(){
        return ResponseEntity.ok(service.listarSolicitacoes().stream().map(SolicitacaoMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Solicitação")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhuma Solicitação Encontrada"),
            @ApiResponse(responseCode = "204",description = "Solicitação Excluída")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirSolicitacao(@PathVariable Integer id){
        service.excluirSolicitacao(id);
        return ResponseEntity.noContent().build();
    }
}
