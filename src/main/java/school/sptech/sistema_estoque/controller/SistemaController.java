package school.sptech.sistema_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.sistema_estoque.dto.classapp.LabelsRequest;
import school.sptech.sistema_estoque.dto.classapp.TagsRequest;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
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
    // GETS
//    @GetMapping("/fornecedores/tipos")
//    public ResponseEntity<Void> listarTipoFornecedores(){}
//
//    @GetMapping("/fornecedores")
//    public ResponseEntity<Void> listarFornecedores(){}

    @GetMapping("/materiais/unidades")
    public ResponseEntity<List<UnidadeMedidaResponse>> listarUnidadesDeMedida(){
        return ResponseEntity.ok(service.listarUnidadeMedida());
    }

    @GetMapping("/materiais")
    public ResponseEntity<List<MaterialResponse>> listarMateriais(){
        return ResponseEntity.ok(service.listarMateriais());
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(service.listarCategorias());
    }

//    @GetMapping("/almoxarifes")
//    public ResponseEntity<Void> listarAlmoxarifes(){}
//
//    @GetMapping("/limites/tipos")
//    public ResponseEntity<Void> listarTiposLimite(){}
//
//    @GetMapping("/limites")
//    public ResponseEntity<Void> listarLimites(){}
//
//    @GetMapping("/historicos/entrada")
//    public ResponseEntity<Void> listarHistoricoEntrada(){}
//
//    @GetMapping("/historicos/saida")
//    public ResponseEntity<Void> listarHistoricoSaida(){}
//
    @GetMapping("/professores")
    public ResponseEntity<List<ProfessorResponse>> listarProfessores(){
        return ResponseEntity.ok(service.listarProfessor());
    }
//
//    // POSTS
//    @PostMapping
//    public ResponseEntity<Void> cadastrarTipoFornecedor(){}
//
//    @PostMapping
//    public ResponseEntity<Void> cadastrarFornecedor(){}

    @PostMapping("/unidadeMedida")
    public ResponseEntity<UnidadeMedidaResponse> cadastrarUnidadeMedida(@RequestBody UnidadeMedidaRequest request){
        return ResponseEntity.status(201).body(service.cadastrarUnidadeMedida(request));
    }

    @PostMapping("/materiais")
    public ResponseEntity<MaterialResponse> cadastrarMaterial(@RequestBody MaterialRequest request){
        return ResponseEntity.status(201).body(service.cadastrarMaterial(request));
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponse> cadastrarCaregoria(@RequestBody CategoriaRequest request){
        return ResponseEntity.status(201).body(service.cadastrarCategoria(request));
    }

//    @PostMapping("/solicitacoes")
//    public ResponseEntity<Void> cadastrarSolicitacao(){}
//
//    @PostMapping
//    public ResponseEntity<Void> cadastrarAlmoxarife(){}
//
//    @PostMapping("/historico")
//    public ResponseEntity<Void> cadastrarTipoLimite(){}
//
//    @PostMapping("/historico")
//    public ResponseEntity<Void> cadastrarLimite(){}
//
//    @PostMapping("/historico")
//    public ResponseEntity<Void> registroHistoricoSaida(){}
//
//    @PostMapping("/historico")
//    public ResponseEntity<Void> registroHistoricoEntrada(){}
//
    @PostMapping("/professores")
    public ResponseEntity<ProfessorResponse> cadastrarProfessor(@RequestBody ProfessorRequest request){
        return ResponseEntity.status(201).body(service.cadastrarProfessor(request));
    }

    @DeleteMapping("/professores/{id}")
    public ResponseEntity<Void> excluirProfessor(@PathVariable Integer id){
        service.excluirProfessor(id);
        return ResponseEntity.noContent().build();

    }

}
