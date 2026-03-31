package school.sptech.sistema_estoque.controller;

import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.service.SistemaService;

@RestController
@RequestMapping("/v1")
public class SistemaController {
    private final SistemaService service;
    public SistemaController(SistemaService service) {
        this.service = service;
    }

    // CLASSAPP - INTREGRAÇÃO COM API
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

    // CRUD - SISTEMA ALMOXARIFADO XINGU
    // GETS
    @GetMapping("/fornecedores/tipos")
    public ResponseEntity<Void> listarTipoFornecedores(){}

    @GetMapping("/fornecedores")
    public ResponseEntity<Void> listarFornecedores(){}

    @GetMapping("/materiais/unidades")
    public ResponseEntity<Void> listarUnidadesDeMedida(){}

    @GetMapping("/materiais")
    public ResponseEntity<Void> listarMateriais(){}

    @GetMapping("/categorias")
    public ResponseEntity<Void> listarCategorias(){}

    @GetMapping("/almoxarifes")
    public ResponseEntity<Void> listarAlmoxarifes(){}

    @GetMapping("/limites/tipos")
    public ResponseEntity<Void> listarTiposLimite(){}

    @GetMapping("/limites")
    public ResponseEntity<Void> listarLimites(){}

    @GetMapping("/historicos/entrada")
    public ResponseEntity<Void> listarHistoricoEntrada(){}

    @GetMapping("/historicos/saida")
    public ResponseEntity<Void> listarHistoricoSaida(){}

    @GetMapping("/professores")
    public ResponseEntity<Void> listarProfessores(){}

    // POSTS
    @PostMapping
    public ResponseEntity<Void> cadastrarTipoFornecedor(){}

    @PostMapping
    public ResponseEntity<Void> cadastrarFornecedor(){}

    @PostMapping("/categorias")
    public ResponseEntity<Void> cadastrarUnidadeMedida(){}

    @PostMapping("/materiais")
    public ResponseEntity<Void> cadastrarMaterial(){}

    @PostMapping("/categorias")
    public ResponseEntity<Void> cadastrarCaregoria(){}

    @PostMapping("/solicitacoes")
    public ResponseEntity<Void> cadastrarSolicitacao(){}

    @PostMapping
    public ResponseEntity<Void> cadastrarAlmoxarife(){}

    @PostMapping("/historico")
    public ResponseEntity<Void> cadastrarTipoLimite(){}

    @PostMapping("/historico")
    public ResponseEntity<Void> cadastrarLimite(){}

    @PostMapping("/historico")
    public ResponseEntity<Void> registroHistoricoSaida(){}

    @PostMapping("/historico")
    public ResponseEntity<Void> registroHistoricoEntrada(){}

    @PostMapping("/professores")
    public ResponseEntity<Void> cadastrarProfessor(){}
}
