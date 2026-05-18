package school.sptech.sistema_estoque.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.dto.estoque.escala.EscalaRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Escala;
import school.sptech.sistema_estoque.port.EscalaPort;

import java.util.List;
import java.util.Optional;

@DisplayName("Testes de Escala")
@ExtendWith(MockitoExtension.class)
public class EscalaServiceTest {
    @Mock
    private EscalaPort escalaPort;

    @InjectMocks
    private EscalaService escalaService;

    @Nested
    @DisplayName("Testes do metodo cadastrarEscala")
    class MetodocadastrarEscala{
        @Test
        @DisplayName("Deve cadastrar escala corretamente")
        void deveCadastrarEscalaCorretamente(){
            EscalaRequest request = new EscalaRequest("Grande");

            Escala escalaSalva = new Escala(1, "Grande");

            Mockito.when(escalaPort.save(Mockito.any(Escala.class)))
                    .thenReturn(escalaSalva);

            Escala escalaCriada = escalaService.cadastrarEscala(request);

            Assertions.assertEquals(escalaSalva, escalaCriada);

        }

        @Test
        @DisplayName("Deve lançar exceção quando request for nulo")
        void deveLancarExcecaoQuandoRequestForNulo(){

            EntidadeInvalidException exception = Assertions.assertThrows(
                    EntidadeInvalidException.class,
                    () -> escalaService.cadastrarEscala(null)
            );

            Assertions.assertEquals(
                    "Escala Inválida",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Deve lançar exception caso a escala já exista")
        void deveLancarExceptionEscalaJaExistente(){

            EscalaRequest request = new EscalaRequest("Grande");

            Escala escalaExistente = new Escala(1, "Grande");

            Mockito.when(escalaPort.findByNomeEscala(request.nomeEscala()))
                    .thenReturn(Optional.of(escalaExistente));

            ResponseStatusException exception = Assertions.assertThrows(
                    ResponseStatusException.class,
                    () -> escalaService.cadastrarEscala(request)
            );

            Assertions.assertEquals(
                    "409 CONFLICT \"Escala com esse nome já existe\"",
                    exception.getMessage()
            );
        }
    }

    @Nested
    @DisplayName("Testes do metodo listarEscala")
    class MetodoListarEscala {

        @Test
        @DisplayName("Deve listar todas as escalas")
        void deveListarEscala() {

            Escala escala1 = new Escala();
            escala1.setNomeEscala("Grande");

            Escala escala2 = new Escala();
            escala2.setNomeEscala("Pequena");

            List<Escala> listaMock =
                    List.of(escala1, escala2);

            Mockito.when(escalaPort.findAll())
                    .thenReturn(listaMock);

            List<Escala> resultado =
                    escalaService.listarEscala();

            Assertions.assertEquals(2, resultado.size());
            Assertions.assertEquals("Grande", resultado.get(0).getNomeEscala());
            Assertions.assertEquals("Pequena", resultado.get(1).getNomeEscala());

            Mockito.verify(escalaPort).findAll();
        }
    }

    @Nested
    @DisplayName("Testes do metodo deletar escala")
    class MetodoDeletarEscala {

        @Test
        @DisplayName("Deve excluir a escala corretamente")
        void deveExcluirEscalaCorretamente() {

            Integer id = 1;

            Escala escala = new Escala();
            escala.setId(id);
            escala.setNomeEscala("Grande");

            Mockito.when(escalaPort.findById(id))
                    .thenReturn(Optional.of(escala));

            escalaService.excluirEscala(id);

            Mockito.verify(escalaPort).delete(escala);
        }

        @Test
        @DisplayName("Deve lançar exceção ao excluir escala inexistente")
        void deveLancarExcecaoAoExcluirEscalaInexistente() {

            Integer id = 1;

            Mockito.when(escalaPort.findById(id))
                    .thenReturn(Optional.empty());

            EntidadeNaoExisteException exception = Assertions.assertThrows(
                    EntidadeNaoExisteException.class,
                    () -> escalaService.excluirEscala(id)
            );

            Assertions.assertEquals(
                    "Escala Não Encontrada",
                    exception.getMessage()
            );
        }
    }
}
