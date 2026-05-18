package school.sptech.sistema_estoque.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.sistema_estoque.dto.estoque.unidade_medida.UnidadeMedidaRequest;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.UnidadeMedida;
import school.sptech.sistema_estoque.port.UnidadeMedidaPort;

import java.util.List;
import java.util.Optional;

@DisplayName("Testes de Unidade de Medida")
@ExtendWith(MockitoExtension.class)
public class UnidadeMedidaServiceTest {

    @Mock
    private UnidadeMedidaPort unidadeMedidaPort;

    @InjectMocks
    private UnidadeMedidaService unidadeMedidaService;

    @Nested
    @DisplayName("Testes do metodo cadastrarUnidadeMedida")
    class MetodoCadastrarUnidadeMedida{
        @Test
        @DisplayName("Deve cadastrar corretamente a unidade de medida")
        void deveCadastrarUnidadeMedida(){
            UnidadeMedidaRequest request = new UnidadeMedidaRequest("Litros");

            UnidadeMedida unidadeMedidaSalva = new UnidadeMedida(1, "Litros");

            Mockito.when(unidadeMedidaPort.save(Mockito.any(UnidadeMedida.class)))
                    .thenReturn(unidadeMedidaSalva);

            UnidadeMedida unidadeMedidaCriada = unidadeMedidaService.cadastrarUnidadeMedida(request);

            Assertions.assertEquals(unidadeMedidaSalva, unidadeMedidaCriada);
        }
    }

    @Nested
    @DisplayName("Testes do metodo listarUnidadeMedida")
    class  MetodoListarUnidadeMedida{
        @Test
        @DisplayName("Deve listar todas as unidades de medida")
        void deveListarUnidadeMedida() {

            UnidadeMedida unidade1 = new UnidadeMedida();
            unidade1.setNomeUnidade("Litros");

            UnidadeMedida unidade2 = new UnidadeMedida();
            unidade2.setNomeUnidade("Quilos");

            List<UnidadeMedida> listaMock =
                    List.of(unidade1, unidade2);

            Mockito.when(unidadeMedidaPort.findAll())
                    .thenReturn(listaMock);

            List<UnidadeMedida> resultado =
                    unidadeMedidaService.listarUnidadeMedida();

            Assertions.assertEquals(2, resultado.size());
            Assertions.assertEquals("Litros", resultado.get(0).getNomeUnidade());
            Assertions.assertEquals("Quilos", resultado.get(1).getNomeUnidade());

            Mockito.verify(unidadeMedidaPort).findAll();
        }
    }

    @Nested
    @DisplayName("Testes do metodo deleta unidade de medidar")
    class metodoDeletarUnidadeMedida{
        @Test
        @DisplayName("Deve excluir a unidade de medida corretamente")
        void deveExcluirUnidadeMedidaCorretamente() {

            Integer id = 1;

            UnidadeMedida unidadeMedida = new UnidadeMedida();
            unidadeMedida.setId(id);
            unidadeMedida.setNomeUnidade("Litros");

            Mockito.when(unidadeMedidaPort.findById(id))
                    .thenReturn(Optional.of(unidadeMedida));

            unidadeMedidaService.excluirUnidadeMedida(id);

            Mockito.verify(unidadeMedidaPort).delete(unidadeMedida);
        }


        @Test
        @DisplayName("Deve lançar exceção ao excluir unidade inexistente")
        void deveLancarExcecaoAoExcluirUnidadeInexistente() {

            Integer id = 1;

            Mockito.when(unidadeMedidaPort.findById(id))
                    .thenReturn(Optional.empty());

            EntidadeNaoExisteException exception = Assertions.assertThrows(
                    EntidadeNaoExisteException.class,
                    () -> unidadeMedidaService.excluirUnidadeMedida(id)
            );

            Assertions.assertEquals("Material Não Encontrado",
                    exception.getMessage()
            );
        }
    }
}
