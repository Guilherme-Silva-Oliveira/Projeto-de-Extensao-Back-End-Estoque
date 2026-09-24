package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.alerta_devolucao.AlertaDevolucaoResponse;
import school.sptech.sistema_estoque.dto.estoque.front.FrontResponse;
import school.sptech.sistema_estoque.dto.estoque.lista_material.ListaMaterialResponse;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.DecisaoSolicitacaoDTO;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.mapper.AlertaDevolucaoMapper;
import school.sptech.sistema_estoque.dto.mapper.ListaMaterialMapper;
import school.sptech.sistema_estoque.dto.mapper.SolicitacaoMapper;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;
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
    public ResponseEntity<Page<SolicitacaoResponse>> listarSolicitacoes(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ){
        var solicitacoes = service.listarSolicitacoes(pageable);
        if (solicitacoes.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(solicitacoes.map(SolicitacaoMapper::toResponse));
    }

    @Operation(summary = "Listar Todas as Solicitações com Materiais Associados")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Solicitação Encontrada"),
            @ApiResponse(responseCode = "200",description = "Solicitações Encontradas")
    })
    @GetMapping("/materiais/{solicitacaoId}")
    public ResponseEntity<List<ListaMaterialResponse>> listarSolicitacoesComMateriais(@PathVariable Integer solicitacaoId){
        var listaMateriais = service.listarMateriaisPorSolicitacao(solicitacaoId);
        if (listaMateriais.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(listaMateriais.stream().map(ListaMaterialMapper::toResponse).toList());
    }

    @Operation(summary = "Listar Todas as Solicitações com Devolução")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Solicitação Encontrada"),
            @ApiResponse(responseCode = "200",description = "Solicitações Encontradas")
    })
    @GetMapping("/devolucoes")
    public ResponseEntity<List<AlertaDevolucaoResponse>> listarDevolucoes(){
        var devolucoes = service.listarDevolucoes();
        if (devolucoes.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(devolucoes.stream().map(AlertaDevolucaoMapper::toResponse).toList());
    }

    @Operation(summary = "Listar Todas as Solicitações Rejeitadas")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhuma Solicitação Encontrada"),
            @ApiResponse(responseCode = "200",description = "Solicitações Encontradas")
    })
    @GetMapping("/rejeitadas")
    public ResponseEntity<List<SolicitacaoResponse>> listarRejeitadas(){
        var solicitacoes = service.listarSolicitacoesRejeitadas();
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
    public ResponseEntity<SolicitacaoResponse> aceitarSolicitacao(
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

    @PostMapping("/finalizarSolicitacao/{id}")
    public ResponseEntity<Void> finalizarSolicitacao(@PathVariable Integer id){
        service.finalizarSolicitacao(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/devolverMaterial/{solicitacaoId}")
    public ResponseEntity<Void> devolverMaterial(@PathVariable Integer solicitacaoId){
        service.devolverMaterial(solicitacaoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relatorio/{professorId}")
    public ResponseEntity<FrontResponse> gerarRelatorio(@PathVariable Integer professorId){
        return ResponseEntity.ok(service.gerarRelatorio(professorId));
    }
}
