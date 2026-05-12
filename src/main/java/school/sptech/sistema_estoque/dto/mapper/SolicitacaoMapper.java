package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.time.LocalDateTime;

public class SolicitacaoMapper {
    public static Solicitacao toEntity(SolicitacaoRequest request, Professor professor, LocalDateTime data){
        Solicitacao entity = new Solicitacao();
        entity.setDataSolicitacao(data);
        entity.setProfessor(professor);
        entity.setDescricao(request.descricao());
        return entity;
    }

    public static SolicitacaoResponse toResponse(Solicitacao entity){
        return new SolicitacaoResponse(entity.getId(), entity.getProfessor(), entity.getDescricao(), entity.getDataSolicitacao());
    }
}
