package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.TagsResponse;
import school.sptech.sistema_estoque.service.SistemaService;

@RestController
@RequestMapping("/sistema")
public class SistemaController {
    private final SistemaService service;
    public SistemaController(SistemaService service) {
        this.service = service;
    }

    @GetMapping("/tags")
    public ResponseEntity<TagsResponse> pegarTags(){
        return ResponseEntity.ok().body(service.getTags());
    }
}
