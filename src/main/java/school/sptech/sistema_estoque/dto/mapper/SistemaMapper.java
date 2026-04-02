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

}
