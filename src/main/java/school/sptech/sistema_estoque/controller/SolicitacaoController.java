package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.DecisaoSolicitacaoDTO;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.service.SolicitacaoService;

import java.util.List;

@RestController
@RequestMapping("/v1/solicitacoes")
@Tag(name = "Solicitações",description = "Operações Relacionadas à Solicitações")
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
    public ResponseEntity<SolicitacaoResponse> cadastrarSolicitacao(@RequestBody SolicitacaoRequest request){
        return ResponseEntity.status(201).body(SolicitacaoMapper.toResponse(service.cadastrarSolicitacao(request)));
    }

    @Operation(summary = "Listar Todas as Solicitações")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Solicitação Encontrada"),
            @ApiResponse(responseCode = "200",description = "Solicitações Encontradas")
    })
    @GetMapping
    public ResponseEntity<List<SolicitacaoResponse>> listarSolicitacoes(){
        var solicitacoes = service.listarSolicitacoes();
        if (solicitacoes.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(solicitacoes.stream().map(SolicitacaoMapper::toResponse).toList());
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

    @PatchMapping("/{id}/decisao")
    public ResponseEntity<SolicitacaoResponse> avaliarSolicitacao(
        @PathVariable Integer id,
        @RequestBody DecisaoSolicitacaoDTO decisao
    ) {
        Solicitacao resultado = service.avaliar(id, decisao.aceita());
        SolicitacaoResponse response = SolicitacaoMapper.toResponse(resultado);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/atualizarStatus/{solicitacaoId}/{status}")
    public ResponseEntity<Void> atualizarStatus(
        @PathVariable Integer solicitacaoId,
        @PathVariable Integer status
    ) {
        service.atualizarStatus(solicitacaoId, status);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verificarPrazos")
    public ResponseEntity<Void> verificarPrazos(){
        service.verificarPrazos();
        return ResponseEntity.ok().build();
    }
}
