package school.sptech.sistema_estoque.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.sistema_estoque.dto.estoque.pedido_saida.PedidoSaidaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.PedidoSaida;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoSaidaId;
import school.sptech.sistema_estoque.observer.MovimentacaoObserver;
import school.sptech.sistema_estoque.port.EscalaPort;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.port.PedidoSaidaPort;
import school.sptech.sistema_estoque.port.SolicitacaoPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaidaServiceTest {

    @Mock
    private PedidoSaidaPort pedidoSaidaPort;

    @Mock
    private MaterialPort materialPort;

    @Mock
    private SolicitacaoPort solicitacaoPort;

    @Mock
    private EscalaPort escalaPort;

    @Mock
    private MovimentacaoObserver observer;

    @InjectMocks
    private SaidaService service;



    // LISTAR PEDIDO SAÍDA

    @Nested
    @DisplayName("Testes do Método Listar Pedido Saída")
    class MetodoListarPedidoSaida {

        @Test
        @DisplayName("Deve Listar Corretamente os Pedidos de Saída")
        void deveListarCorretamentePedidosDeSaida() {

            PedidoSaida pedidoSaida = new PedidoSaida();

            when(pedidoSaidaPort.findAll()).thenReturn(List.of(pedidoSaida));

            List<PedidoSaida> resultado = service.listarPedidoSaida();

            assertNotNull(resultado);
            assertFalse(resultado.isEmpty());
            verify(pedidoSaidaPort, times(1)).findAll();
        }

        @Test
        @DisplayName("Deve Lançar Exception Quando Não Há Pedidos de Saída")
        void deveLancarExceptionQuandoNaoHaPedidosDeSaida() {

            when(pedidoSaidaPort.findAll()).thenReturn(List.of());


            assertThrows(EntidadeNaoExisteException.class, () ->
                    service.listarPedidoSaida());
        }
    }



    // CADASTRAR PEDIDO SAÍDA

    @Nested
    @DisplayName("Testes do Método Cadastrar Pedido Saída")
    class MetodoCadastrarPedidoSaida {

        @Test
        @DisplayName("Deve Cadastrar Pedido de Saída Corretamente")
        void deveCadastrarPedidoDeSaidaCorretamente() {

            Material material = new Material();
            material.setNomeMaterial("Caneta");
            material.setQuantidade(50);

            Solicitacao solicitacao = new Solicitacao();
            Escala escala = new Escala();
            PedidoSaida pedidoSaida = new PedidoSaida();

            PedidoSaidaRequest request = new PedidoSaidaRequest(1, 1, 10, LocalDateTime.now(), 1, 1);

            when(materialPort.findById(request.materialId())).thenReturn(Optional.of(material));
            when(solicitacaoPort.findById(request.solicitacaoId())).thenReturn(Optional.of(solicitacao));
            when(escalaPort.findById(request.escalaId())).thenReturn(Optional.of(escala));
            when(pedidoSaidaPort.save(any(PedidoSaida.class))).thenReturn(pedidoSaida);

            PedidoSaida resultado = service.cadastrarPedidoSaida(request);

            assertNotNull(resultado);
            verify(materialPort, times(1)).save(material);
            verify(pedidoSaidaPort, times(1)).save(any(PedidoSaida.class));
            verify(observer, times(1)).gerarLogs(anyString());
            verify(observer, times(1)).atualizar(anyString());
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Request Nulo")
        void deveLancarExceptionRequestNulo() {

            assertThrows(EntidadeInvalidException.class, () ->
                    service.cadastrarPedidoSaida(null));
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Material Não Encontrado")
        void deveLancarExceptionMaterialNaoEncontrado() {

            PedidoSaidaRequest request = new PedidoSaidaRequest(1, 1, 10, LocalDateTime.now(), 1, 1);

            when(materialPort.findById(request.materialId())).thenReturn(Optional.empty());


            assertThrows(EntidadeInvalidException.class, () ->
                    service.cadastrarPedidoSaida(request));

            verify(pedidoSaidaPort, never()).save(any());
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Solicitação Não Encontrada")
        void deveLancarExceptionSolicitacaoNaoEncontrada() {

            Material material = new Material();
            material.setQuantidade(50);

            PedidoSaidaRequest request = new PedidoSaidaRequest(1, 1, 10, LocalDateTime.now(), 1, 1);

            when(materialPort.findById(request.materialId())).thenReturn(Optional.of(material));
            when(solicitacaoPort.findById(request.solicitacaoId())).thenReturn(Optional.empty());


            assertThrows(EntidadeInvalidException.class, () ->
                    service.cadastrarPedidoSaida(request));

            verify(pedidoSaidaPort, never()).save(any());
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Escala Não Encontrada")
        void deveLancarExceptionEscalaNaoEncontrada() {

            Material material = new Material();
            material.setQuantidade(50);

            Solicitacao solicitacao = new Solicitacao();

            PedidoSaidaRequest request = new PedidoSaidaRequest(1, 1, 10, LocalDateTime.now(), 1, 1);

            when(materialPort.findById(request.materialId())).thenReturn(Optional.of(material));
            when(solicitacaoPort.findById(request.solicitacaoId())).thenReturn(Optional.of(solicitacao));
            when(escalaPort.findById(request.escalaId())).thenReturn(Optional.empty());


            assertThrows(EntidadeInvalidException.class, () ->
                    service.cadastrarPedidoSaida(request));

            verify(pedidoSaidaPort, never()).save(any());
        }
    }



    // EXCLUIR PEDIDO SAÍDA

    @Nested
    @DisplayName("Testes do Método Excluir Pedido Saída")
    class MetodoExcluirPedidoSaida {

        @Test
        @DisplayName("Deve Excluir Pedido de Saída Corretamente")
        void deveExcluirPedidoDeSaidaCorretamente() {

            PedidoSaida pedidoSaida = new PedidoSaida();

            when(pedidoSaidaPort.findById(any(PedidoSaidaId.class))).thenReturn(Optional.of(pedidoSaida));

            service.excluirPedidoSaida(1, 1);

            verify(pedidoSaidaPort, times(1)).delete(pedidoSaida);
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Pedido de Saída Não Encontrado")
        void deveLancarExceptionPedidoDeSaidaNaoEncontrado() {

            when(pedidoSaidaPort.findById(any(PedidoSaidaId.class))).thenReturn(Optional.empty());


            assertThrows(EntidadeNaoExisteException.class, () ->
                    service.excluirPedidoSaida(1, 1));

            verify(pedidoSaidaPort, never()).delete(any());
        }
    }
}