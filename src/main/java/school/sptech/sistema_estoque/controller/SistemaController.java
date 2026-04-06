package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.dto.codigo.CodigoRequest;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.service.ClassAppService;
import school.sptech.sistema_estoque.service.SistemaService;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class SistemaController {
    private final ClassAppService serviceClassApp;
    private final SistemaService service;
    public SistemaController(ClassAppService serviceClassApp, SistemaService service) {
        this.serviceClassApp = serviceClassApp;
        this.service = service;
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
}
