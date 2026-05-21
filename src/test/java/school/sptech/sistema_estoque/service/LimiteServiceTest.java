package school.sptech.sistema_estoque.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import school.sptech.sistema_estoque.dto.estoque.tipo_limite.TipoLimiteRequest;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Limite;
import school.sptech.sistema_estoque.model.estoque.Material;
import school.sptech.sistema_estoque.model.estoque.TipoLimite;
import school.sptech.sistema_estoque.port.LimitePort;
import school.sptech.sistema_estoque.port.MaterialPort;
import school.sptech.sistema_estoque.port.TipoLimitePort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LimiteServiceTest {

    @Mock
    private LimitePort limitePort;

    @Mock
    private TipoLimitePort tipoLimitePort;

    @Mock
    private MaterialPort materialPort;

    @InjectMocks
    private LimiteService limiteService;

    @Nested
    @DisplayName("Testes dos metodos de Listar")
    class MetodoListar {

        @Test
        @DisplayName("Deve listar os Limites Corretamente")
        void deveListarLimitesCorretamente() {
            // given
            Limite limite = new Limite(
                    1,
                    "limite",
                    new TipoLimite(),
                    new Material()
            );
            // when
            when(limitePort.findAll()).thenReturn(List.of(limite));

            List<Limite> resultado = limiteService.listarLimites();
            // then
            assertEquals(List.of(limite), resultado);
        }

        @Test
        @DisplayName("Deve listar os TipoLimite Corretamente")
        void deveListarTipoLimitesCorretamente() {
            // given
            TipoLimite tipoLimite = new TipoLimite();
            tipoLimite.setTipo("tipo");
            // when
            when(tipoLimitePort.findAll()).thenReturn(List.of(tipoLimite));

            List<TipoLimite> resultado = limiteService.listarTiposLimite();
            // then
            assertEquals(List.of(tipoLimite), resultado);
        }
    }

    @Nested
    @DisplayName("Testes dos metodos de Cadastrar")
    class MetodoCadastrar {

        @Test
        @DisplayName("Deve Cadastrar Limite Corretamente")
        void deveCadastrarTipoLimitesCorretamente() {
            
            TipoLimiteRequest tipoLimiteRequest = new TipoLimiteRequest("tipo");
            
            TipoLimite tipoLimite = new TipoLimite();
            tipoLimite.setTipo("tipo");
            

            when(tipoLimitePort.save(any(TipoLimite.class))).thenReturn(tipoLimite);

            TipoLimite resultado = limiteService.cadastrarTipoLimite(tipoLimiteRequest);


            assertEquals(tipoLimite.getTipo(), resultado.getTipo());
        }

        @Test
        @DisplayName("Deve Lançar Exception quando Request for null")
        void deveLancarExceptionRequestNull() {

            assertThrows(EntidadeInvalidException.class, () ->
                    limiteService.cadastrarTipoLimite(null));
        }
    }

    @Nested
    @DisplayName("Deve Excluir Corretamente")
    class MetodoExcluir {

        @Test
        @DisplayName("Deve Excluir Limite Corretamente")
        void deveExcluirLimiteCorretamente() {

            Limite limite = new Limite();
            limite.setId(1);

            when(limitePort.findById(1)).thenReturn(Optional.of(limite));
            limiteService.excluirLimite(1);


            verify(limitePort, times(1)).delete(limite);
        }
        
        @Test
        @DisplayName("Deve Lançar Exception quando Limite não existir")
        void deveLancarExceptionLimiteNaoExistir() {


            when(limitePort.findById(1)).thenReturn(Optional.empty());

            assertThrows(
                    EntidadeNaoExisteException.class, () ->
                    limiteService.excluirLimite(1));
        }
    }

}
