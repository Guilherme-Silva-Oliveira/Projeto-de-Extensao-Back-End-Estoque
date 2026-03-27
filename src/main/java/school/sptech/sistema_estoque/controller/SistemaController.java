package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.LabelsRequest;
import school.sptech.sistema_estoque.dto.TagsRequest;
import school.sptech.sistema_estoque.service.SistemaService;

@RestController
@RequestMapping("/sistema")
public class SistemaController {
    private final SistemaService service;
    public SistemaController(SistemaService service) {
        this.service = service;
    }

    @GetMapping("/tags")
    public ResponseEntity<TagsRequest> pegarTags(){
        return ResponseEntity.ok(service.getTags());
    }

    @GetMapping("/students")
    public ResponseEntity<String> pegarEstudantes() {return ResponseEntity.ok(service.getStudents());}

    @GetMapping("/staffs")
    public ResponseEntity<String> pegarAdministradores() {return ResponseEntity.ok(service.getStaffs());}

    @GetMapping("/labels")
    public ResponseEntity<LabelsRequest> pegarLabels() {return ResponseEntity.ok(service.getLabels());}
}
