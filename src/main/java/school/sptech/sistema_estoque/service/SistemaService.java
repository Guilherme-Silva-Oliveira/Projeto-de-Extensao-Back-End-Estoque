package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.dto.mapper.SistemaMapper;
import school.sptech.sistema_estoque.exception.*;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class SistemaService {
    private final CategoriaRepository catrepository;
    private final MaterialRepository matrepository;
    private final EstoqueRepository estrepository;
    private final UnidadeMedidaRepository unirepository;

    private final SistemaMapper mapper;
    private final ProfessorRepository professorRepository;
    private final SolicitacaoRepository solicitacaoRepository;
    private final PedidoSaidaRepository pedidoSaidaRepository;
    private final EscalaRepository escalaRepository;
    private final TipoLimiteRepository tipoLimiteRepository;
    private final LimiteRepository limiteRepository;
    private final AlmoxarifadoRepository almoxarifadoRepository;
    private final AlmoxarifeRepository almoxarifeRepository;
    private final TipoFornecedorRepository tipoFornecedorRepository;
    private final FornecedorRepository fornecedorRepository;
    private final PedidoEntradaRepository pedidoEntradaRepository;

    public SistemaService(CategoriaRepository catrepository, MaterialRepository matrepository, EstoqueRepository estrepository, UnidadeMedidaRepository unirepository, SistemaMapper mapper, ProfessorRepository professorRepository, SolicitacaoRepository solicitacaoRepository, PedidoSaidaRepository pedidoSaidaRepository, EscalaRepository escalaRepository, TipoLimiteRepository tipoLimiteRepository, LimiteRepository limiteRepository, AlmoxarifadoRepository almoxarifadoRepository, AlmoxarifeRepository almoxarifeRepository, TipoFornecedorRepository tipoFornecedorRepository, FornecedorRepository fornecedorRepository, PedidoEntradaRepository pedidoEntradaRepository) {
        this.catrepository = catrepository;
        this.matrepository = matrepository;
        this.estrepository = estrepository;
        this.unirepository = unirepository;
        this.mapper = mapper;
        this.professorRepository = professorRepository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.pedidoSaidaRepository = pedidoSaidaRepository;
        this.escalaRepository = escalaRepository;
        this.tipoLimiteRepository = tipoLimiteRepository;
        this.limiteRepository = limiteRepository;
        this.almoxarifadoRepository = almoxarifadoRepository;
        this.almoxarifeRepository = almoxarifeRepository;
        this.tipoFornecedorRepository = tipoFornecedorRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.pedidoEntradaRepository = pedidoEntradaRepository;
    }

    // -------------- CATEGORIA --------------
    public CategoriaResponse cadastrarCategoria(CategoriaRequest request){
        if (request==null){throw new InvalidCategoriaRequestException("Categoria Inválida");} // VALIDAÇÃO INICIAL
        Categoria c = mapper.toCategoriaEntity(request); // CONVERSÃO REQUEST - ENTIDADE CATEGORIA
        Categoria salvo = catrepository.save(c);
        return mapper.toCategoriaResponse(salvo);
    }
    public List<CategoriaResponse> listarCategorias(){
        // CONVERTENDO ENTIDADE - RESPONSE CATEGORIA + EXIBIR
        return catrepository.findAll().stream()
                .map(mapper::toCategoriaResponse)
                .toList();
    }
    public void excluirCategoria(Integer id){
        Optional<Categoria> opt = catrepository.findById(id);
        if (opt.isEmpty()){throw new CategoriaNaoExisteException("Categoria Não Encontrada");}
        catrepository.delete(opt.get());
    }
    // -------------- FIM CATEGORIA --------------

    // -------------- MATERIAL --------------
    public MaterialResponse cadastrarMaterial(MaterialRequest request){
        if (request==null){throw new InvalidMaterialRequestException("Material Inválido");} // VALIDAÇÃO INICIAL

        // VALIDAÇÕES OPTIONAL -- POSSÍVEL DE OTIMIZAR
        Optional<Categoria> catOpt = catrepository.findById(request.idCategoria());
        if (catOpt.isEmpty()){throw new CategoriaNaoExisteException("Categoria Não Encontrada");}
        Optional<Estoque> estOpt = estrepository.findById(request.idEstoque());
        if (estOpt.isEmpty()){throw new EstoqueNaoExisteException("Estoque Não Encontrada");}
        Optional<UnidadeMedida> uniOpt = unirepository.findById(request.idUnidadeMedida());
        if (uniOpt.isEmpty()){throw new UnidadeMedidaNaoExisteException("Categoria Não Encontrada");}

        // CONVERSÃO OPTIONAL - ENTIDADE CATEGORIA, ESTOQUE E UNIDADE DE MEDIDA
        Categoria c = catOpt.get();
        Estoque e = estOpt.get();
        UnidadeMedida u = uniOpt.get();
        Material m = mapper.toMaterialEntity(request,c,e,u); // CONVERSÃO REQUEST - ENTIDADE MATERIAL
        matrepository.save(m);
        return mapper.toMaterialResponse(m);
    }
    public List<MaterialResponse> listarMateriais(){
        // CONVERTENDO ENTIDADE - RESPONSE MATERIAL + EXIBIR
        return matrepository.findAll().stream()
                .map(mapper::toMaterialResponse)
                .toList();
    }
    public void excluirMaterial(Integer id){
        Optional<Material> opt = matrepository.findById(id);
        if (opt.isEmpty()){throw new MaterialNaoExisteException("Material Não Encontrado");}
        matrepository.delete(opt.get());
    }
    // -------------- FIM MATERIAL --------------

    // -------------- UNIDADE MEDIDA --------------
    public UnidadeMedidaResponse cadastrarUnidadeMedida(UnidadeMedidaRequest request){
        if (request==null){throw new InvalidUnidadeMedidaException("Unidade de Medida Inválido");} // VALIDAÇÃO INICIAL
        UnidadeMedida unidade = mapper.toUnidadeMedidaEntity(request);
        unirepository.save(unidade);
        return mapper.toUnidadeMedidaResponse(unidade);
    }
    public List<UnidadeMedidaResponse> listarUnidadeMedida(){
        return unirepository.findAll().stream()
                .map(mapper::toUnidadeMedidaResponse)
                .toList();
    }
    public void excluirUnidadeMedida(Integer id){
        Optional<UnidadeMedida> opt = unirepository.findById(id);
        if (opt.isEmpty()){throw new UnidadeMedidaNaoExisteException("Material Não Encontrado");}
        unirepository.delete(opt.get());
    }
    // -------------- FIM UNIDADE MEDIDA --------------

    // -------------- PROFESSOR --------------
    // CRUD PARA PROFESSOR
    public ProfessorResponse cadastrarProfessor(ProfessorRequest request){
        if (request == null){
            throw new InvalidProfessorRequestException("Professor Inválido");
        }

        Professor professor = mapper.toProfessorEntity(request);
        professorRepository.save(professor);
        return mapper.toProfessorResponse(professor);
    }

    public List<ProfessorResponse> listarProfessor(){
        return professorRepository.findAll().stream()
                .map(mapper::toProfessorResponse)
                .toList();
    }

    public void excluirProfessor(Integer id){
        Optional<Professor> opt = professorRepository.findById(id);
        if (opt.isEmpty()){
            throw new InvalidProfessorRequestException("Professor Não Encontrada");
        }
        professorRepository.delete(opt.get());
    }


    // -------------- FIM PROFESSOR --------------

    // -------------- SOLICITAÇÃO --------------
    // CRUD PARA SOLICITAÇÃO
        public List<SolicitacaoResponse> listarSolicitacao() {
        return solicitacaoRepository.findAll().stream()
                .map(mapper::toSolicitacaoResponse)
                .toList();
    }

    public SolicitacaoResponse cadastrarSolicitacao(SolicitacaoRequest request) {
        if (request == null){
            throw new InvalidSolicitacaoRequestException("Solicitacao Inválida");
        }

        Optional<Professor> professorOptional = professorRepository.findById(request.idProfessor());
        if (professorOptional.isEmpty()){throw new InvalidProfessorRequestException("Professor não encontrado");}

        Solicitacao solicitacao = mapper.toSolicitacaoEntity(request, professorOptional.get());
        solicitacaoRepository.save(solicitacao);
        return mapper.toSolicitacaoResponse(solicitacao);
    }

    // -------------- FIM SOLICITAÇÃO --------------

    // -------------- ESCALA --------------
    public EscalaResponse cadastrarEscala(EscalaRequest request){
    if (request == null){ throw new InvalidEscalaRequestException("Escala Inválida"); } // VALIDAÇÃO INICIAL

    Escala e = mapper.toEscalaEntity(request); // CONVERSÃO REQUEST - ENTIDADE ESCALA
    Escala salvo = escalaRepository.save(e);

    return mapper.toEscalaResponse(salvo);
    }

    public List<EscalaResponse> listarEscala(){
        // CONVERTENDO ENTIDADE - RESPONSE ESCALA + EXIBIR
        return escalaRepository.findAll().stream()
                .map(mapper::toEscalaResponse)
                .toList();
    }
    // -------------- FIM ESCALA --------------

    // -------------- LIMITE + TIPO_LIMITE --------------
    // CRUD PARA LIMITE
    public TipoLimiteResponse cadastrarTipoLimite(TipoLimiteRequest request){
    if (request == null){ throw new InvalidTipoLimiteRequestException("Tipo de Limite Inválido"); } // VALIDAÇÃO INICIAL

    TipoLimite tl = mapper.toTipoLimiteEntity(request); // CONVERSÃO REQUEST - ENTIDADE TIPO LIMITE
    TipoLimite salvo = tipoLimiteRepository.save(tl);

        return mapper.toTipoLimiteResponse(salvo);
    }

    public List<TipoLimiteResponse> listarTiposLimite(){
        // CONVERTENDO ENTIDADE - RESPONSE TIPO LIMITE + EXIBIR
        return tipoLimiteRepository.findAll().stream()
                .map(mapper::toTipoLimiteResponse)
                .toList();
    }

    public LimiteResponse cadastrarLimite(LimiteRequest request){
    if (request == null){ throw new InvalidLimiteRequestException("Limite Inválido"); } // VALIDAÇÃO INICIAL

    // VALIDAÇÃO DA EXISTÊNCIA DO TIPO LIMITE
    Optional<TipoLimite> tipoOptional = tipoLimiteRepository.findById(request.idTipoLimite());
    if (tipoOptional.isEmpty()){ throw new InvalidTipoLimiteRequestException("Tipo de Limite não encontrado"); }

    Limite l = mapper.toLimiteEntity(request, tipoOptional.get()); // CONVERSÃO REQUEST - ENTIDADE LIMITE
    Limite salvo = limiteRepository.save(l);

    return mapper.toLimiteResponse(salvo);
    }

    public List<LimiteResponse> listarLimites(){
        // CONVERTENDO ENTIDADE - RESPONSE LIMITE + EXIBIR
        return limiteRepository.findAll().stream()
                .map(mapper::toLimiteResponse)
                .toList();
    }
    // -------------- FIM LIMITE + TIPO_LIMITE --------------

    // -------------- ALMOXARIFADO --------------
    public AlmoxarifadoResponse cadastrarAlmoxarifado(AlmoxarifadoRequest request) {
        if (request == null) {
            throw new InvalidAlmoxarifadoRequestException("Almoxarifado invalido");
        }

        if (request.idsLimites() == null || request.idsLimites().isEmpty()) {
            throw new InvalidLimiteRequestException("Limites nao informados");
        }

        List<Limite> limites = limiteRepository.findAllById(request.idsLimites());
        if (limites.size() != request.idsLimites().size()) {
            throw new InvalidLimiteRequestException("Limite nao encontrado");
        }

        Almoxarifado almoxarifado = mapper.toAlmoxarifadoEntity(request, limites);
        Almoxarifado salvo = almoxarifadoRepository.save(almoxarifado);
        return mapper.toAlmoxarifadoResponse(salvo);
    }

    public List<AlmoxarifadoResponse> listarAlmoxarifados() {
        return almoxarifadoRepository.findAll().stream()
                .map(mapper::toAlmoxarifadoResponse)
                .toList();
    }
    // -------------- FIM ALMOXARIFADO --------------

    // -------------- ALMOXARIFE --------------
    public AlmoxarifeResponse cadastrarAlmoxarife(AlmoxarifeRequest request) {
        if (request == null) {
            throw new InvalidAlmoxarifeRequestException("Almoxarife invalido");
        }

        Optional<Almoxarifado> almoxarifadoOptional = almoxarifadoRepository.findById(request.idAlmoxarifado());
        if (almoxarifadoOptional.isEmpty()) {
            throw new InvalidAlmoxarifadoRequestException("Almoxarifado nao encontrado");
        }

        Optional<Estoque> estoqueOptional = estrepository.findById(request.idEstoque());
        if (estoqueOptional.isEmpty()) {
            throw new EstoqueNaoExisteException("Estoque nao encontrado");
        }

        Almoxarife almoxarife = mapper.toAlmoxarifeEntity(request, almoxarifadoOptional.get(), estoqueOptional.get());
        Almoxarife salvo = almoxarifeRepository.save(almoxarife);
        return mapper.toAlmoxarifeResponse(salvo);
    }

    public List<AlmoxarifeResponse> listarAlmoxarifes() {
        return almoxarifeRepository.findAll().stream()
                .map(mapper::toAlmoxarifeResponse)
                .toList();
    }
    // -------------- FIM ALMOXARIFE --------------

    // -------------- FORNECEDOR + TIPO_FORNECEDOR --------------
    public TipoFornecedorResponse cadastrarTipoFornecedor(TipoFornecedorRequest request) {
        if (request == null) {
            throw new InvalidTipoFornecedorRequestException("Tipo fornecedor invalido");
        }

        TipoFornecedor tipoFornecedor = mapper.toTipoFornecedorEntity(request);
        TipoFornecedor salvo = tipoFornecedorRepository.save(tipoFornecedor);
        return mapper.toTipoFornecedorResponse(salvo);
    }

    public List<TipoFornecedorResponse> listarTipoFornecedores() {
        return tipoFornecedorRepository.findAll().stream()
                .map(mapper::toTipoFornecedorResponse)
                .toList();
    }

    public FornecedorResponse cadastrarFornecedor(FornecedorRequest request) {
        if (request == null) {
            throw new InvalidFornecedorRequestException("Fornecedor invalido");
        }

        Optional<TipoFornecedor> tipoOptional = tipoFornecedorRepository.findById(request.idTipoFornecedor());
        if (tipoOptional.isEmpty()) {
            throw new InvalidTipoFornecedorRequestException("Tipo fornecedor nao encontrado");
        }

        Fornecedor fornecedor = mapper.toFornecedorEntity(request, tipoOptional.get());
        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return mapper.toFornecedorResponse(salvo);
    }

    public List<FornecedorResponse> listarFornecedores() {
        return fornecedorRepository.findAll().stream()
                .map(mapper::toFornecedorResponse)
                .toList();
    }
    // -------------- FIM FORNECEDOR + TIPO_FORNECEDOR --------------

    // -------------- PEDIDO_ENTRADA --------------
    public PedidoEntradaResponse cadastrarPedidoEntrada(PedidoEntradaRequest request) {
        if (request == null) {
            throw new InvalidPedidoEntradaRequestException("Pedido entrada invalido");
        }

        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(request.fornecedorId());
        if (fornecedorOptional.isEmpty()) {
            throw new InvalidFornecedorRequestException("Fornecedor nao encontrado");
        }

        Optional<Material> materialOptional = matrepository.findById(request.materialId());
        if (materialOptional.isEmpty()) {
            throw new InvalidMaterialRequestException("Material nao encontrado");
        }

        PedidoEntrada pedidoEntrada = mapper.toPedidoEntradaEntity(request, fornecedorOptional.get(), materialOptional.get());
        PedidoEntrada salvo = pedidoEntradaRepository.save(pedidoEntrada);
        return mapper.toPedidoEntradaResponse(salvo);
    }

    public List<PedidoEntradaResponse> listarPedidosEntrada() {
        return pedidoEntradaRepository.findAll().stream()
                .map(mapper::toPedidoEntradaResponse)
                .toList();
    }
    // -------------- FIM PEDIDO_ENTRADA --------------



    // -------------- PEDIDO_SAIDA + PEDIDO ENTRADA --------------
    // CRUD PEDIDO_SAIDA + PEDIDO_ENTRADA
        public List<PedidoSaidaResponse> listarPedidoSaida() {
            return pedidoSaidaRepository.findAll().stream()
                    .map(mapper::toPedidoSaidaResponse)
                    .toList();
        }

    public PedidoSaidaResponse cadastrarPedidoSaida(PedidoSaidaRequest request) {
        if (request == null){
            throw new InvalidPedidoSaidaRequestException("Pedido Saida Inváldo");
        }
        Optional<Material> materialOptional = matrepository.findById(request.materialId());
        if (materialOptional.isEmpty()) {
            throw new InvalidMaterialRequestException("Material não encontrado");
        }
        Material material = materialOptional.get();

        Optional<Solicitacao> solicitacaoOptional = solicitacaoRepository.findById(request.solicitacaoId());
        if (solicitacaoOptional.isEmpty()) {
            throw new InvalidSolicitacaoRequestException("Solicitação de origem não encontrada");
        }
        Solicitacao solicitacao = solicitacaoOptional.get();

        Optional<Escala> escalaOptional = escalaRepository.findById(request.escalaId());
        if (escalaOptional.isEmpty()) {
            throw new InvalidEscalaRequestException("Escala associada não encontrada");
        }
        Escala escala = escalaOptional.get();

        PedidoSaida pedidoSaida = mapper.toPedidoSaidaEntity(request, material, solicitacao, escala);
        pedidoSaidaRepository.save(pedidoSaida);
        return mapper.toPedidoSaidaResponse(pedidoSaida);

    }


    // -------------- FIM PEDIDO_SAIDA + PEDIDO_ENTRADA --------------
}
