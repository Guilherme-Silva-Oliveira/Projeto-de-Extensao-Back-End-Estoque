package school.sptech.sistema_estoque.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorRequest;
import school.sptech.sistema_estoque.dto.estoque.professor.ProfessorResponse;
import school.sptech.sistema_estoque.dto.mapper.ProfessorMapper;
import school.sptech.sistema_estoque.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {
    private final ProfessorService service;
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastrar um Professor")
    @ApiResponses({
            @ApiResponse(responseCode = "400",description = "Corpo para Cadastro Inválido"),
            @ApiResponse(responseCode = "201",description = "Professor Cadastrado")
    })
    @PostMapping
    public ResponseEntity<ProfessorResponse> cadastrarProfessor(@RequestBody ProfessorRequest request){
        var professor = service.cadastrarProfessor(request);
        return ResponseEntity.status(201).body(ProfessorMapper.toResponse(professor));
    }

    @Operation(summary = "Listar Todos os Professores")
    @ApiResponses({
            @ApiResponse(responseCode = "204",description = "Nenhum Professor Encontrado"),
            @ApiResponse(responseCode = "200",description = "Professores Encontrados")
    })
    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listarProfessores(){
        var professores = service.listarProfessor();
        return ResponseEntity.ok(professores.stream().map(ProfessorMapper::toResponse).toList());
    }

    @Operation(summary = "Excluir Professor")
    @ApiResponses({
            @ApiResponse(responseCode = "404",description = "Nenhum Professor Encontrado"),
            @ApiResponse(responseCode = "204",description = "Professor Excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProfessor(@PathVariable Integer id){
        service.excluirProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
