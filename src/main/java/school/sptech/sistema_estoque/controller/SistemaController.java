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
    // GETS
    @GetMapping("/fornecedores")
    public ResponseEntity<List<FornecedorResponse>> listarFornecedores() {
        return ResponseEntity.ok(service.listarFornecedores());
    }

    @GetMapping("/fornecedores/tipos")
    public ResponseEntity<List<TipoFornecedorResponse>> listarTipoFornecedores() {
        return ResponseEntity.ok(service.listarTipoFornecedores());
    }

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

    @GetMapping("/almoxarifados")
    public ResponseEntity<List<AlmoxarifadoResponse>> listarAlmoxarifados() {
        return ResponseEntity.ok(service.listarAlmoxarifados());
    }
    
    @GetMapping("/almoxarifes")
    public ResponseEntity<List<AlmoxarifeResponse>> listarAlmoxarifes() {
        return ResponseEntity.ok(service.listarAlmoxarifes());
    }


    @GetMapping("/professores")
    public ResponseEntity<List<ProfessorResponse>> listarProfessores(){
        return ResponseEntity.ok(service.listarProfessor());
    }

    @GetMapping("/solicitacoes")
    public ResponseEntity<List<SolicitacaoResponse>> listarSolicitacao(){
        return ResponseEntity.ok(service.listarSolicitacao());
    }

    @GetMapping("/pedido-saida")
    public ResponseEntity<List<PedidoSaidaResponse>> listarPedidos(){
        return ResponseEntity.ok(service.listarPedidoSaida());
    }
    
    @GetMapping("/pedido-entrada")
    public ResponseEntity<List<PedidoEntradaResponse>> listarPedidosEntrada() {
        return ResponseEntity.ok(service.listarPedidosEntrada());
    }

    @GetMapping("/escalas")
    public ResponseEntity<List<EscalaResponse>> listarEscala(){
        return ResponseEntity.ok(service.listarEscala());
    }

    @GetMapping("/limites/tipos")
    public ResponseEntity<List<TipoLimiteResponse>> listarTipoLimite(){
        return ResponseEntity.ok(service.listarTiposLimite());
    }

    @GetMapping("/limites")
    public ResponseEntity<List<LimiteResponse>> listarLimites(){
        return ResponseEntity.ok(service.listarLimites());
    }

    @PostMapping("/fornecedores/tipos")
    public ResponseEntity<TipoFornecedorResponse> cadastrarTipoFornecedor(@RequestBody TipoFornecedorRequest request) {
        return ResponseEntity.status(201).body(service.cadastrarTipoFornecedor(request));
    }

    @PostMapping("/fornecedores")
    public ResponseEntity<FornecedorResponse> cadastrarFornecedor(@RequestBody FornecedorRequest request) {
        return ResponseEntity.status(201).body(service.cadastrarFornecedor(request));
    }

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
//    public ResponseEntity<SolicitacaoResponse> cadastrarSolicitacao(@RequestBody SolicitacaoRequest request){
//        return ResponseEntity.status(201).body(service.cadastrarSolicitacao(request));
//    }

    @PostMapping("/almoxarifados")
    public ResponseEntity<AlmoxarifadoResponse> cadastrarAlmoxarifado(@RequestBody AlmoxarifadoRequest request) {
        return ResponseEntity.status(201).body(service.cadastrarAlmoxarifado(request));
    }
    
    @PostMapping("/almoxarifes")
    public ResponseEntity<AlmoxarifeResponse> cadastrarAlmoxarife(@RequestBody AlmoxarifeRequest request) {
        return ResponseEntity.status(201).body(service.cadastrarAlmoxarife(request));
    }

    @PostMapping("/professores")
    public ResponseEntity<ProfessorResponse> cadastrarProfessor(@RequestBody ProfessorRequest request){
        return ResponseEntity.status(201).body(service.cadastrarProfessor(request));
    }

    @DeleteMapping("/professores/{id}")
    public ResponseEntity<Void> excluirProfessor(@PathVariable Integer id){
        service.excluirProfessor(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/pedido-saida")
    public ResponseEntity<PedidoSaidaResponse> cadastrarPedidoSaida(@RequestBody PedidoSaidaRequest request){
        return ResponseEntity.status(201).body(service.cadastrarPedidoSaida(request));
    }
    
//    @PostMapping("/pedido-entrada")
//    public ResponseEntity<PedidoEntradaResponse> cadastrarPedidoEntrada(@RequestBody PedidoEntradaRequest request) {
//        return ResponseEntity.status(201).body(service.cadastrarPedidoEntrada(request));
//    }

    @PostMapping("/escalas")
    public ResponseEntity<EscalaResponse> cadastrarEscala(@RequestBody EscalaRequest request){
        return ResponseEntity.status(201).body(service.cadastrarEscala(request));
    }

    @PostMapping("/tipos-limite")
    public ResponseEntity<TipoLimiteResponse> cadastrarTipoLimite(@RequestBody TipoLimiteRequest request){
        return ResponseEntity.status(201).body(service.cadastrarTipoLimite(request));
    }

    @PostMapping("/limites")
    public ResponseEntity<LimiteResponse> cadastrarLimite(@RequestBody LimiteRequest request){
        return ResponseEntity.status(201).body(service.cadastrarLimite(request));
    }

    // ------- CÓDIGO DE BARRAS -------
    @PostMapping("/codigos")
    public ResponseEntity<PedidoEntradaResponse> registrarEntrada(@RequestBody CodigoRequest codigo, @RequestBody PedidoEntradaRequest pedidoEntrada){
        System.out.println(">>> Estoque recebeu: " + codigo.codigo());
        service.cadastrarPedidoEntrada(pedidoEntrada,codigo);
        return ResponseEntity.ok(service.cadastrarPedidoEntrada(pedidoEntrada,codigo));
    }
    // ------- FIM CÓDIGO DE BARRAS -------

    // ------- SOLICITAÇÃO VIA IA -------
    @PostMapping("/ia")
    public ResponseEntity<SolicitacaoResponse> registrarSolicitacao(@RequestBody SolicitacaoIARequest solicitacao){
        System.out.println(">>> Estoque recebeu: "+ solicitacao);
        service.cadastrarSolicitacao(solicitacao);
        return ResponseEntity.ok(service.cadastrarSolicitacao(solicitacao));
    }
    // ------- FIM SOLICITAÇÃO VIA IA -------
}
