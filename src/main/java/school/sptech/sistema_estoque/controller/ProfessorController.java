package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.ProfessorRequest;
import school.sptech.sistema_estoque.dto.estoque.ProfessorResponse;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {
    private final ProfessorService service;
    private final SistemaMapper mapper;
    public ProfessorController(ProfessorService service, SistemaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProfessorResponse> cadastrarProfessor(@RequestBody ProfessorRequest request){
        var professor = service.cadastrarProfessor(request);
        return ResponseEntity.ok(mapper.toProfessorResponse(professor));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listarProfessores(){
        var professores = service.listarProfessor();
        return ResponseEntity.ok(professores.stream().map(mapper::toProfessorResponse).toList());
    }
}
