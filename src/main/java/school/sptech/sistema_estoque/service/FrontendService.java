
package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.movimentacao.MovimentacaoFront;
import school.sptech.sistema_estoque.dto.mapper.MovimentacaoMapper;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.port.ListaMaterialPort;
import school.sptech.sistema_estoque.port.PedidoEntradaPort;
import school.sptech.sistema_estoque.port.SolicitacaoPort;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FrontendService {
    private final PedidoEntradaPort entradaPort;
    private final ListaMaterialPort listaPort;

    public List<MovimentacaoFront> listarMovimentacoes() {
        List<PedidoEntrada> entradas = entradaPort.findAll();
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
}
