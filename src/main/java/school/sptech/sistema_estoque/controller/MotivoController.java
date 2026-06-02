package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.motivo.MotivoRequest;
import school.sptech.sistema_estoque.dto.estoque.motivo.MotivoResponse;
import school.sptech.sistema_estoque.dto.mapper.MotivoMapper;
import school.sptech.sistema_estoque.service.MotivoService;

import java.util.List;

@RestController
@RequestMapping("/v1/motivos")
@Tag(name = "Motivo",description = "Operações Relacionadas à Motivo")
public class MotivoController {
    private final MotivoService service;
    public MotivoController(MotivoService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Motivo")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Motivo Inválido"),
            @ApiResponse(responseCode = "201",description = "Motivo Cadastrado")
    })
    @PostMapping
    public ResponseEntity<MotivoResponse> cadastrarMotivo(@RequestBody MotivoRequest request){
        var motivo = service.cadastrarMotivo(request);
        return ResponseEntity.status(201).body(MotivoMapper.toResponse(motivo));
    }

    @Operation(summary = "Listar Todos os Motivos")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Motivo Encontrado"),
            @ApiResponse(responseCode = "200",description = "Motivos Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<MotivoResponse>> listarMotivos(){
        var motivos = service.listarMotivos();
        if (motivos.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(motivos.stream().map(MotivoMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Motivo")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Motivo Encontrado"),
            @ApiResponse(responseCode = "204",description = "Motivo Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMotivo(@PathVariable Integer id){
        service.excluirMotivo(id);
        return ResponseEntity.noContent().build();
    }
}
