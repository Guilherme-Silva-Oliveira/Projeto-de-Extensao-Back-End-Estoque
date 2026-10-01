
package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.movimentacao.MovimentacaoFront;
import school.sptech.sistema_estoque.dto.front.AlertaParaFront;
import school.sptech.sistema_estoque.dto.mapper.MovimentacaoMapper;
import school.sptech.sistema_estoque.enums.StatusAlertaSolicitacao;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.AlertaDevolucaoPort;
import school.sptech.sistema_estoque.port.AlertaSolicitacaoPort;
import school.sptech.sistema_estoque.port.ListaMaterialPort;
import school.sptech.sistema_estoque.port.PedidoEntradaPort;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FrontendService {
    private final PedidoEntradaPort entradaPort;
    private final ListaMaterialPort listaPort;
    private final AlertaDevolucaoPort alertaDevolucaoPort;
    private final AlertaSolicitacaoPort alertaSolicitacaoPort;

    public List<MovimentacaoFront> listarMovimentacoes(Pageable pageable) {
        Page<PedidoEntrada> entradas = entradaPort.findAll(pageable);
        List<ListaMaterial> listaMateriais = listaPort.findAll();
        List<MovimentacaoFront> movimentacoes = new ArrayList<>();
        for (PedidoEntrada entrada : entradas) {
            movimentacoes.add(MovimentacaoMapper.fromEntradaEntity(entrada));
        }
        for (ListaMaterial material : listaMateriais) {
            movimentacoes.add(MovimentacaoMapper.fromSolicitacaotoEntity(material));
        }
        return movimentacoes;
    }

    public List<AlertaParaFront> listarAlertas() {
        List<AlertaSolicitacao> alertasSolicitacao = alertaSolicitacaoPort.findAll();
        List<AlertaDevolucao> alertasDevolucao = alertaDevolucaoPort.findAll();
        List<AlertaParaFront> alertasParaFront = new ArrayList<>();
        for (AlertaSolicitacao alerta : alertasSolicitacao) {
            if (!alerta.getSolicitacao().getAlerta().contains(StatusAlertaSolicitacao.TUDO_CERTO.getDescricao())){
                if (!alerta.getResolvido()){
                    alertasParaFront.add(fromAlertaSolicitacao(alerta));
                }
            }
        }
        for (AlertaDevolucao alerta : alertasDevolucao) {
            if (!alerta.getDevolvido()){
                alertasParaFront.add(fromAlertaDevolucao(alerta));
            }
        }
        return alertasParaFront;
    }

    public AlertaParaFront fromAlertaSolicitacao(AlertaSolicitacao alerta){
        return new AlertaParaFront(
                "Solicitação",
                alerta.getSolicitacao().getProfessor().getNome(),
                alerta.getSolicitacao().getAlerta(),
                alerta.getResolvido(),
                alerta.getId()
        );
    }

    public AlertaParaFront fromAlertaDevolucao(AlertaDevolucao alerta){
        return new AlertaParaFront(
                "Devolução",
                alerta.getSolicitacao().getProfessor().getNome(),
                alerta.getDescricao(),
                alerta.getDevolvido(),
                alerta.getId()
        );
    }

    public void atualizarAlerta(Integer id, String tipoAlerta){
        if (tipoAlerta.equals("Devolução")){
            AlertaDevolucao devolucao = alertaDevolucaoPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Alerta de Devolução Não Encontrado"));
            devolucao.setDevolvido(true);
            alertaDevolucaoPort.saveAlerta(devolucao);
        }else{
            AlertaSolicitacao solicitacao = alertaSolicitacaoPort.findById(id).orElseThrow(()-> new EntidadeNaoExisteException("Alerta de Devolução Não Encontrado"));
            solicitacao.setResolvido(true);
            alertaSolicitacaoPort.saveAlerta(solicitacao);
        }
    }
}
