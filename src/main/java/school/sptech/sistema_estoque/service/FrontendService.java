
package school.sptech.sistema_estoque.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.estoque.movimentacao.MovimentacaoFront;
import school.sptech.sistema_estoque.dto.mapper.MovimentacaoMapper;
import school.sptech.sistema_estoque.model.estoque.ListaMaterial;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.port.ListaMaterialPort;
import school.sptech.sistema_estoque.port.PedidoEntradaPort;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FrontendService {
    private final PedidoEntradaPort entradaPort;
    private final ListaMaterialPort listaPort;

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
}
