package school.sptech.sistema_estoque.dto.mapper;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.dto.estoque.*;
import school.sptech.sistema_estoque.model.estoque.*;

@Component
public class SistemaMapper {
    // CATEGORIA
    public Categoria toCategoriaEntity(CategoriaRequest request){
        Categoria c = new Categoria();
        c.setNomeCategoria(request.nomeCategoria());
        return c;
    }
    public CategoriaResponse toCategoriaResponse(Categoria entity){
        return new CategoriaResponse(entity.getId(),entity.getNomeCategoria());
    }

    // MATERIAL
    public Material toMaterialEntity(MaterialRequest request, Categoria categoria, Estoque estoque, UnidadeMedida unidadeMedida){
        Material m = new Material();
        m.setCategoria(categoria);
        m.setEstoque(estoque);
        m.setUnidadeMedida(unidadeMedida);
        return m;
    }
    public MaterialResponse toMaterialResponse(Material entity){
        return new MaterialResponse(entity.getId(),entity.getCategoria());
    }

    // UNIDADE DE MEDIDA
    public UnidadeMedida toUnidadeMedidaEntity(UnidadeMedidaRequest request){
        UnidadeMedida u = new UnidadeMedida();
        u.setNomeUnidade(request.nomeUnidade());
        return u;
    }
    public UnidadeMedidaResponse toUnidadeMedidaResponse(UnidadeMedida entity){
        return new UnidadeMedidaResponse(entity.getId(),entity.getNomeUnidade());
    }

    // PROFESSOR
    public Professor toProfessorEntity(ProfessorRequest professorRequest){
        Professor entity = new Professor();
        entity.setEmail(professorRequest.email());
        entity.setNome(professorRequest.nome());
        entity.setTelefone(professorRequest.telefone());
        return entity;
    }

    public ProfessorResponse toProfessorResponse(Professor entity){
        return new ProfessorResponse(entity.getId(), entity.getNome(), entity.getEmail(), entity.getTelefone());
    }

    // SOLICITACAO
    public Solicitacao toSolicitacaoEntity(SolicitacaoRequest request, Professor professor){
        Solicitacao entity = new Solicitacao();
        entity.setEscala(request.escala());
        entity.setDescricao(request.descricao());
        entity.setDataSolicitacao(request.dataSolicitacao());
        entity.setProfessor(professor);
        return entity;
    }

    public SolicitacaoResponse toSolicitacaoResponse(Solicitacao entity){
        return new SolicitacaoResponse(entity.getId(), entity.getProfessor(), entity.getEscala(), entity.getDescricao(), entity.getDataSolicitacao());
    }

    // PEDIDO_SAIDA
    public PedidoSaida toPedidoSaidaEntity(PedidoSaidaRequest request, Material material, Solicitacao solicitacao, Escala escala) {
    PedidoSaida entity = new PedidoSaida();
    entity.setMaterial(material);
    entity.setSolicitacao(solicitacao);
    entity.setEscala(escala);
    entity.setQuantidade(request.quantidade());
    entity.setDataSaida(request.dataSaida());

    return entity;
    }

    public PedidoSaidaResponse toPedidoSaidaResponse(PedidoSaida entity) {
    return new PedidoSaidaResponse(
        entity.getMaterial(),
        entity.getSolicitacao(),
        entity.getQuantidade(),
        entity.getDataSolicitacao(),
        entity.getEscala(),
        entity.getDataSaida()
    );
    }

    // ESCALA
    public Escala toEscalaEntity(EscalaRequest request){
        Escala e = new Escala();
        e.setNomeEscala(request.nomeEscala());
        return e;
    }

    public EscalaResponse toEscalaResponse(Escala entity){
        return new EscalaResponse(entity.getId(), entity.getNomeEscala());
    }

    // TIPO LIMITE
    public TipoLimite toTipoLimiteEntity(TipoLimiteRequest request){
        TipoLimite tl = new TipoLimite();
        tl.setTipo(request.tipo());
        return tl;
    }

    public TipoLimiteResponse toTipoLimiteResponse(TipoLimite entity){
        return new TipoLimiteResponse(entity.getId(), entity.getTipo());
    }

    // LIMITE
    public Limite toLimiteEntity(LimiteRequest request, TipoLimite tipoLimite){
        Limite l = new Limite();
        l.setLimite(request.limite());
        l.setTipoLimite(tipoLimite);
        return l;
    }

    public LimiteResponse toLimiteResponse(Limite entity){
        return new LimiteResponse(
            entity.getId(),
            entity.getLimite(),
            toTipoLimiteResponse(entity.getTipoLimite())
        );
    }

    // ALMOXARIFADO
    public Almoxarifado toAlmoxarifadoEntity(AlmoxarifadoRequest request, java.util.List<Limite> limites) {
        Almoxarifado a = new Almoxarifado();
        a.setNumeroSala(request.numeroSala());
        a.setLimites(limites);
        return a;
    }

    public AlmoxarifadoResponse toAlmoxarifadoResponse(Almoxarifado entity) {
        java.util.List<Integer> limiteIds = entity.getLimites() == null
                ? java.util.List.of()
                : entity.getLimites().stream().map(Limite::getId).toList();
        java.util.List<LimiteResponse> limiteResponses = entity.getLimites() == null
                ? java.util.List.of()
                : entity.getLimites().stream().map(this::toLimiteResponse).toList();
        return new AlmoxarifadoResponse(
            entity.getId(),
            entity.getNumeroSala(),
            limiteIds,
            limiteResponses
        );
    }

    // ESTOQUE
    public EstoqueResponse toEstoqueResponse(Estoque entity) {
        return new EstoqueResponse(entity.getId());
    }

    // ALMOXARIFE
    public Almoxarife toAlmoxarifeEntity(AlmoxarifeRequest request, Almoxarifado almoxarifado, Estoque estoque) {
        Almoxarife a = new Almoxarife();
        a.setNome(request.nome());
        a.setEmail(request.email());
        a.setTelefone(request.telefone());
        a.setSenha(request.senha());
        a.setAlmoxarifado(almoxarifado);
        a.setEstoque(estoque);
        return a;
    }

    public AlmoxarifeResponse toAlmoxarifeResponse(Almoxarife entity) {
        Integer almoxarifadoId = entity.getAlmoxarifado() != null ? entity.getAlmoxarifado().getId() : null;
        Integer estoqueId = entity.getEstoque() != null ? entity.getEstoque().getId() : null;
        AlmoxarifadoResponse almoxarifadoResponse = entity.getAlmoxarifado() != null ? toAlmoxarifadoResponse(entity.getAlmoxarifado()) : null;
        EstoqueResponse estoqueResponse = entity.getEstoque() != null ? toEstoqueResponse(entity.getEstoque()) : null;
        return new AlmoxarifeResponse(
            entity.getId(),
            entity.getNome(),
            entity.getEmail(),
            entity.getTelefone(),
            entity.getSenha(),
            almoxarifadoId,
            estoqueId,
            almoxarifadoResponse,
            estoqueResponse
        );
    }

    // TIPO FORNECEDOR
    public TipoFornecedor toTipoFornecedorEntity(TipoFornecedorRequest request) {
        TipoFornecedor tf = new TipoFornecedor();
        tf.setNomeTipo(request.nomeTipo());
        return tf;
    }

    public TipoFornecedorResponse toTipoFornecedorResponse(TipoFornecedor entity) {
        return new TipoFornecedorResponse(entity.getId(), entity.getNomeTipo());
    }

    // FORNECEDOR
    public Fornecedor toFornecedorEntity(FornecedorRequest request, TipoFornecedor tipoFornecedor) {
        Fornecedor f = new Fornecedor();
        f.setNome(request.nome());
        f.setEmail(request.email());
        f.setTelefone(request.telefone());
        f.setCnpjCpf(request.cnpjCpf());
        f.setTipoFornecedor(tipoFornecedor);
        return f;
    }

    public FornecedorResponse toFornecedorResponse(Fornecedor entity) {
        Integer tipoFornecedorId = entity.getTipoFornecedor() != null ? entity.getTipoFornecedor().getId() : null;
        TipoFornecedorResponse tipoFornecedorResponse = entity.getTipoFornecedor() != null ? toTipoFornecedorResponse(entity.getTipoFornecedor()) : null;
        return new FornecedorResponse(
            entity.getId(),
            entity.getNome(),
            entity.getEmail(),
            entity.getTelefone(),
            entity.getCnpjCpf(),
            tipoFornecedorId,
            tipoFornecedorResponse
        );
    }

    // PEDIDO ENTRADA
    public PedidoEntrada toPedidoEntradaEntity(PedidoEntradaRequest request, Fornecedor fornecedor, Material material) {
        PedidoEntrada p = new PedidoEntrada();
        p.setFornecedor(fornecedor);
        p.setMaterial(material);
        p.setQuantidade(request.quantidade());
        p.setDataEntrada(request.dataEntrada());
        return p;
    }

    public PedidoEntradaResponse toPedidoEntradaResponse(PedidoEntrada entity) {
        Integer fornecedorId = entity.getFornecedor() != null ? entity.getFornecedor().getId() : null;
        Integer materialId = entity.getMaterial() != null ? entity.getMaterial().getId() : null;
        FornecedorResponse fornecedorResponse = entity.getFornecedor() != null ? toFornecedorResponse(entity.getFornecedor()) : null;
        MaterialResponse materialResponse = entity.getMaterial() != null ? toMaterialResponse(entity.getMaterial()) : null;
        return new PedidoEntradaResponse(
            entity.getId(),
            fornecedorId,
            materialId,
            entity.getQuantidade(),
            entity.getDataEntrada(),
            fornecedorResponse,
            materialResponse
        );
    }

}
