package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.time.LocalDateTime;

public class SolicitacaoMapper {
    public static Solicitacao toEntity(SolicitacaoIARequest request, Professor professor, LocalDateTime data){
        Solicitacao entity = new Solicitacao();
        //VALIDAR COM EQUIPE
//        entity.setEscala(request.escala());
        entity.setDataSolicitacao(data);
        entity.setProfessor(professor);
        entity.setDescricao(request.nome_material()+" para "+request.nome_professor());
        return entity;
    }

    public static SolicitacaoResponse toResponse(Solicitacao entity){
        return new SolicitacaoResponse(entity.getId(), entity.getProfessor(), entity.getEscala(), entity.getDescricao(), entity.getDataSolicitacao());
    }
}
