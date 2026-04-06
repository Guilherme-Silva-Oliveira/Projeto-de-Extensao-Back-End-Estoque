package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.dto.codigo.CodigoRequest;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.service.ClassAppService;

@RestController
@RequestMapping("/v1")
public class SistemaController {
    private final ClassAppService serviceClassApp;
    public SistemaController(ClassAppService serviceClassApp) {
        this.serviceClassApp = serviceClassApp;
    }

    // CLASSAPP - INTREGRAÇÃO COM API (PARA EXCLUIR)
    @GetMapping("/tags")
    public ResponseEntity<TagsRequest> pegarTags(){
        return ResponseEntity.ok(serviceClassApp.getTags());
    }
    @GetMapping("/students")
    public ResponseEntity<String> pegarEstudantes() {return ResponseEntity.ok(serviceClassApp.getStudents());}
    @GetMapping("/staffs")
    public ResponseEntity<String> pegarAdministradores() {return ResponseEntity.ok(serviceClassApp.getStaffs());}
    @GetMapping("/labels")
    public ResponseEntity<LabelsRequest> pegarLabels() {return ResponseEntity.ok(serviceClassApp.getLabels());}

    // CRUD - SISTEMA ALMOXARIFADO XINGU
    // DADO QUE O CRUD FOI FEITO NOS SERVICES, PRECISAMOS TESTAR ADEQUADAMENTE NO CONTROLLER
    @PostMapping("/codigos")
    public ResponseEntity<CodigoRequest> pegarCodigoBarras(@RequestBody CodigoRequest codigo){
        System.out.println(">>> Estoque recebeu: " + codigo.codigo());
        return ResponseEntity.ok(codigo);
    }

    @PostMapping("/ia")
    public ResponseEntity<SolicitacaoRequest> receberSolicitacao(@RequestBody SolicitacaoRequest solicitacao){
        System.out.println(">>> Estoque recebeu: "+ solicitacao);
        return ResponseEntity.ok(solicitacao);
    }
}
