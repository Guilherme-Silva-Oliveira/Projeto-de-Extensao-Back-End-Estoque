package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.estoque.setor_estoque.SetorEstoqueRequest;
import school.sptech.sistema_estoque.dto.estoque.setor_estoque.SetorEstoqueResponse;
import school.sptech.sistema_estoque.dto.mapper.AlmoxarifadoMapper;
import school.sptech.sistema_estoque.dto.mapper.SetorEstoqueMapper;
import school.sptech.sistema_estoque.model.estoque.SetorEstoque;
import school.sptech.sistema_estoque.service.AlmoxarifadoService;
import school.sptech.sistema_estoque.service.SetorEstoqueService;

import java.util.List;

@RestController
@RequestMapping("/v1/setores")
@Tag(name = "Setor de Estoque",description = "Operações Relacionadas ao Setor de Estoque")
public class SetorEstoqueController {
    private final SetorEstoqueService service;
    public SetorEstoqueController(SetorEstoqueService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Setor Estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Setor de Estoque Cadastrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SetorEstoqueResponse> cadastrarSetorEstoque(@RequestBody SetorEstoqueRequest request){
        var setorEstoque = service.cadastrarSetorEstoque(request);
        return ResponseEntity.status(201).body(SetorEstoqueMapper.toResponse(setorEstoque));
    }

    @Operation(summary = "Listar Todos os Almoxarifados")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Almoxarifado Encontrado"),
            @ApiResponse(responseCode = "200",description = "Almoxarifados Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<SetorEstoqueResponse>> listarSetores(){
        var setores = service.listarSetores();
        if (setores.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(setores.stream().map(SetorEstoqueMapper::toResponse).collect(java.util.stream.Collectors.toList()));
    }

    @Operation(summary = "Excluir Setor de Estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Setor de Estoque Encontrado"),
            @ApiResponse(responseCode = "204",description = "Setor de Estoque Excluído")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirSetor(@PathVariable Integer id){
        service.excluirSetor(id);
        return ResponseEntity.noContent().build();
    }
}

