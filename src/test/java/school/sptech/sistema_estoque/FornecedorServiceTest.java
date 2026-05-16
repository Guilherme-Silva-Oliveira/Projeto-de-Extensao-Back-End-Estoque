package school.sptech.sistema_estoque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import school.sptech.sistema_estoque.dto.estoque.fornecedor.FornecedorRequest;
import school.sptech.sistema_estoque.dto.estoque.tipo_fornecedor.TipoFornecedorRequest;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Fornecedor;
import school.sptech.sistema_estoque.model.estoque.PedidoEntrada;
import school.sptech.sistema_estoque.model.estoque.TipoFornecedor;
import school.sptech.sistema_estoque.port.FornecedorPort;
import school.sptech.sistema_estoque.port.TipoFornecedorPort;
import school.sptech.sistema_estoque.service.FornecedorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DisplayName("Testes de Fornecedor")
@ExtendWith(MockitoExtension.class)
public class FornecedorServiceTest {

    @Mock
    private FornecedorPort fornecedorPort;
    @Mock
    private TipoFornecedorPort tipoFornecedorPort;
    @InjectMocks
    private FornecedorService fornecedorService;


    @Nested
    @DisplayName("Testes do metodo cadastrarTipoFornecedor")
    class MetodoCadastrarTipoFornecedor{

        @Test
        @DisplayName("Não há conflitos")
        void deveCadastrarTipoFornecedor(){

            TipoFornecedorRequest tipoFornecedorRequest = new TipoFornecedorRequest("Tipo 01");
            TipoFornecedor tipoFornecedor = new TipoFornecedor(null, tipoFornecedorRequest.nomeTipo());

            Mockito.when(tipoFornecedorPort.save(Mockito.any(TipoFornecedor.class)))
                    .thenReturn(tipoFornecedor);
            TipoFornecedor tipoFornecedoCriado = fornecedorService.cadastrarTipoFornecedor(tipoFornecedorRequest);

            Assertions.assertSame(tipoFornecedor, tipoFornecedoCriado);

        }
    }

    @Nested
    @DisplayName("Testes do metodo listarTipoFornecedores")
    class MetodolistarTipoFornecedores{

        @Test
        @DisplayName("Há tipoFornecedor pra listar")
        void develistarTipoFornecedores(){

            TipoFornecedorRequest tipoFornecedorRequest = new TipoFornecedorRequest("Tipo 01");
            TipoFornecedor tipoFornecedor = new TipoFornecedor(null, tipoFornecedorRequest.nomeTipo());

            List<TipoFornecedor> tipoFornecedorList = List.of(tipoFornecedor);

            Mockito.when(tipoFornecedorPort.findAll())
                    .thenReturn(tipoFornecedorList);
            List<TipoFornecedor> tipoFornecedorListCriado = fornecedorService.listarTipoFornecedores();

            Assertions.assertEquals(1, tipoFornecedorList.size());

        }

        @Test
        @DisplayName("Não há tipoFornecedor pra listar")
        void naoDevelistarTipoFornecedores(){

            List<TipoFornecedor> tipoFornecedorList = new ArrayList<>();

            Mockito.when(tipoFornecedorPort.findAll())
                    .thenReturn(tipoFornecedorList);
            List<TipoFornecedor> tipoFornecedorListCriado = fornecedorService.listarTipoFornecedores();

            Assertions.assertEquals(0, tipoFornecedorList.size());
        }
    }

    @Nested
    @DisplayName("Testes do metodo excluirTipoFornecedor")
    class MetodoExcluirTipoFornecedor{

        @Test
        @DisplayName("Deve excluir corretamente")
        void deveExcluir(){

            Integer id = 1;

            TipoFornecedorRequest tipoFornecedorRequest = new TipoFornecedorRequest("Tipo 01");
            TipoFornecedor tipoFornecedor = new TipoFornecedor(id, tipoFornecedorRequest.nomeTipo());

            Mockito.when(tipoFornecedorPort.findById(id))
                    .thenReturn(Optional.of(tipoFornecedor));

            Assertions.assertDoesNotThrow(() -> fornecedorService.excluirTipoFornecedor(id));

            Mockito.verify(tipoFornecedorPort, Mockito.times(1)).delete(tipoFornecedor);

        }

        @Test
        @DisplayName("Não deve excluir corretamente")
        void naoDeveExcluir(){

            Integer id = 1;
            ;

            Mockito.when(tipoFornecedorPort.findById(id))
                    .thenReturn(Optional.empty());

            EntidadeNaoExisteException exception =
                    Assertions.assertThrows(
                            EntidadeNaoExisteException.class,
                            () -> fornecedorService.excluirTipoFornecedor(id)
                    );

            Assertions.assertEquals("Tipo Fornecedor Não Encontrado",
                    exception.getMessage());

        }

    }

    @Nested
    @DisplayName("Testes do metodo cadastrarFornecedor")
    class metodoCadastrarFornecedor{

        @Test
        @DisplayName("Deve cadastrar forneceodor corretamente")
        void deveCadastrarFornecedor(){

            Integer idTipoFornecedor = 1;

            TipoFornecedorRequest tipoFornecedorRequest = new TipoFornecedorRequest("Tipo 01");
            TipoFornecedor tipoFornecedor = new TipoFornecedor(idTipoFornecedor, tipoFornecedorRequest.nomeTipo());


            List<PedidoEntrada> pedidoEntradas = new ArrayList<>();

            Integer ID = 1;

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setEmail("Email@.");
            fornecedor.setTipoFornecedor(tipoFornecedor);
            fornecedor.setNome("Nome");
            fornecedor.setTelefone("111111");
            fornecedor.setId(null);
            fornecedor.setPedidosEntrada(pedidoEntradas);

            FornecedorRequest fornecedorRequest = new FornecedorRequest("Nome", "Email@.", "111111", idTipoFornecedor);

            Mockito.when(tipoFornecedorPort.findById(idTipoFornecedor))
                            .thenReturn(Optional.of(tipoFornecedor));

            Mockito.when(fornecedorPort.existsByEmailAndTelefone("Email@.", "111111"))
                    .thenReturn(false);

            Mockito.when(fornecedorPort.save(Mockito.any(Fornecedor.class)))
                    .thenReturn(fornecedor);
            Fornecedor fornecedoCriado = fornecedorService.cadastrarFornecedor(fornecedorRequest);

            Assertions.assertEquals(fornecedor.getEmail(), fornecedoCriado.getEmail());
            Assertions.assertEquals(fornecedor.getTelefone(), fornecedoCriado.getTelefone());
            Assertions.assertEquals(fornecedor.getNome(), fornecedoCriado.getNome());
            Assertions.assertEquals(fornecedor.getTipoFornecedor(), fornecedoCriado.getTipoFornecedor());
            Assertions.assertEquals(fornecedor.getId(), fornecedoCriado.getId());

        }
    }

}
