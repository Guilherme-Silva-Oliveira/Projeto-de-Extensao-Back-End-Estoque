package school.sptech.sistema_estoque.dto.mapper;

import school.sptech.sistema_estoque.dto.mq.AlertaMessage;
import school.sptech.sistema_estoque.dto.mq.AlertaVerificacao;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

public class AlertaVerificacaoMapper {

    public static AlertaVerificacao toEntity(AlertaMessage alertaMessage) {
        AlertaVerificacao a = new AlertaVerificacao();
        a.setTipo(alertaMessage.tipo());
        a.setNome(alertaMessage.nome());
        a.setDescricao(alertaMessage.descricao());
        a.setDataCriacao(alertaMessage.criadoEm());
        return a;
    }
}
