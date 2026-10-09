package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.mapper.AlertaVerificacaoMapper;
import school.sptech.sistema_estoque.dto.mq.AlertaMessage;
import school.sptech.sistema_estoque.dto.mq.AlertaVerificacao;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.port.AlertaVerificacaoPort;
import school.sptech.sistema_estoque.port.SolicitacaoPort;

@Service
@AllArgsConstructor
public class AlertasService {
    private final SolicitacaoPort solicitacaoPort;
    private final AlertaVerificacaoPort alertaVerificacaoPort;

    public void processarAlerta(AlertaMessage mensagem) {
        if (mensagem == null) {throw new EntidadeInvalidException("Mensagem Inválida");}
        System.out.println("Alerta Recebido: "+mensagem);
        AlertaVerificacao a = AlertaVerificacaoMapper.toEntity(mensagem);
        Solicitacao solicitacao = solicitacaoPort.findById(mensagem.referenciaId()).orElseThrow(()-> new EntidadeNaoExisteException("Solicitacao Não Encontrada"));
        a.setSolicitacao(solicitacao);
        alertaVerificacaoPort.saveAlerta(a);
    }
}
