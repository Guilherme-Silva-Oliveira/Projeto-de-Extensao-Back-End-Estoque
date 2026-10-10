package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoFinalizadaResponse;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoReprovadaResponse;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoResponse;
import school.sptech.sistema_estoque.enums.StatusSolicitacao;
import school.sptech.sistema_estoque.model.estoque.Motivo;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.time.LocalDateTime;

public class SolicitacaoMapper {
    public static Solicitacao toEntity(SolicitacaoRequest request, Professor professor, Motivo motivo, LocalDateTime data, StatusSolicitacao status){
        Solicitacao entity = new Solicitacao();
        entity.setDataSolicitacao(data);
        entity.setProfessor(professor);
        entity.setInteligenciaArtificialId(request.inteligenciaArtificialId());
        entity.setDescricao(request.descricao());
        entity.setMateriais(request.materiais());
        entity.setMotivo(motivo);
        entity.setDataParaEnvio(request.dataParaEnvio());
        entity.setAlerta(request.alerta());
        return entity;
    }

    public static SolicitacaoResponse toResponse(Solicitacao entity){
        return new SolicitacaoResponse(
                entity.getId(),
                entity.getDescricao(),
                entity.getDataSolicitacao(),
                entity.getDataParaEnvio(),
                entity.getAlerta(),
                entity.getMotivo().getDescricao(),
                entity.getProfessor().getNome()
        );
    }

    public static SolicitacaoReprovadaResponse toReprovadaResponse(Solicitacao entity, LocalDateTime dataReprovacao){
        return new SolicitacaoReprovadaResponse(
                entity.getId(),
                entity.getDescricao(),
                entity.getDataSolicitacao(),
                entity.getDataParaEnvio(),
                entity.getAlerta(),
                entity.getMotivo().getDescricao(),
                entity.getProfessor().getNome(),
                dataReprovacao
        );
    }


    public static SolicitacaoFinalizadaResponse toFinalizadaResponse(Solicitacao entity, LocalDateTime dataFinalizacao){
        return new SolicitacaoFinalizadaResponse(
                entity.getId(),
                entity.getDescricao(),
                entity.getDataSolicitacao(),
                entity.getDataParaEnvio(),
                entity.getAlerta(),
                entity.getMotivo().getDescricao(),
                entity.getProfessor().getNome(),
                dataFinalizacao
        );
    }
}