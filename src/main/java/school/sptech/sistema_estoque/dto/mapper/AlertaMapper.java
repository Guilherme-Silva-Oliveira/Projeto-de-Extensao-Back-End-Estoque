package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

public class AlertaMapper {

    public static AlertaDevolucao toEntity(Solicitacao solicitacao) {
        AlertaDevolucao a = new AlertaDevolucao();
        a.setDescricao(solicitacao.getDescricao());
        a.setProfessor(solicitacao.getProfessor());
        a.setSolicitacao(solicitacao);
        a.setDevolvido(false);
        return a;
    }
}
