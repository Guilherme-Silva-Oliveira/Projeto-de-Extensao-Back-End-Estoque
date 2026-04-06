package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.estoque.MaterialRequest;
import school.sptech.sistema_estoque.dto.estoque.MaterialResponse;
import school.sptech.sistema_estoque.dto.estoque.ProfessorRequest;
import school.sptech.sistema_estoque.dto.estoque.ProfessorResponse;
import school.sptech.sistema_estoque.service.MaterialService;
import school.sptech.sistema_estoque.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {
    private final ProfessorService service;
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProfessorResponse> cadastrarProfessor(@RequestBody ProfessorRequest request){
        return ResponseEntity.ok(service.cadastrarProfessor(request));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listarProfessores(){
        return ResponseEntity.ok(service.listarProfessor());
    }
}
