package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoRequest;
import school.sptech.sistema_estoque.dto.estoque.solicitacao.SolicitacaoResponse;
import school.sptech.sistema_estoque.dto.ia.SolicitacaoIARequest;
import school.sptech.sistema_estoque.enums.StatusSolicitacao;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.Motivo;
import school.sptech.sistema_estoque.model.estoque.Professor;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.time.LocalDateTime;

public class SolicitacaoMapper {
    public static Solicitacao toEntity(SolicitacaoRequest request, Professor professor, Motivo motivo, LocalDateTime data, Material material, StatusSolicitacao status){
        Solicitacao entity = new Solicitacao();
        entity.setDataSolicitacao(data);
        entity.setProfessor(professor);
        entity.setMaterial(material);
        entity.setQuantidade(request.quantidade());
        entity.setInteligenciaArtificialId(request.inteligenciaArtificialId());
        entity.setDescricao(request.descricao());
        entity.setMotivo(motivo);
        entity.setDataParaEnvio(request.dataParaEnvio());
        entity.setDeveDevolver(request.deveDevolver());
        return entity;
    }

    public static SolicitacaoResponse toResponse(Solicitacao entity){
        return new SolicitacaoResponse(entity.getId(),entity.getQuantidade(),entity.getDescricao(), entity.getDataSolicitacao(), entity.getDataParaEnvio());
    }
}
