package school.sptech.sistema_estoque.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.sistema_estoque.config.GerenciadorTokenJwt;
import school.sptech.sistema_estoque.dto.estoque.almoxarifado.AlmoxarifadoResponse;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeRequest;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeResponse;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeToken;
import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeUpdateRequest;
import school.sptech.sistema_estoque.exception.EntidadeConflictException;
import school.sptech.sistema_estoque.exception.EntidadeInvalidException;
import school.sptech.sistema_estoque.exception.EntidadeNaoExisteException;
import school.sptech.sistema_estoque.model.estoque.Almoxarifado;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.port.AlmoxarifadoPort;
import school.sptech.sistema_estoque.port.AlmoxarifePort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlmoxarifeServiceTest {
    @Mock
    private AlmoxarifadoPort almoxarifadoPort;

    @Mock
    private AlmoxarifePort almoxarifePort;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AlmoxarifeService service;

    @Nested
    @DisplayName("Testes do Método Cadastrar")
    class MetodoCadastrar{
        @Test
        @DisplayName("Deve Cadastrar um Almoxarife Corretamente")
        void deveCadastrarAlmoxarife(){
            Almoxarife almoxarife = new Almoxarife();
            Almoxarifado almoxarifado = new Almoxarifado();
            almoxarife.setNome("Gabriel Furtado");
            AlmoxarifeRequest request = new AlmoxarifeRequest(
                    "Gabriel Furtado","gabriel@gmail.com","1140028922","40028922",1);
            when(almoxarifePort.save(any(Almoxarife.class))).thenReturn(almoxarife);
            when(almoxarifadoPort.findById(anyInt())).thenReturn(Optional.of(almoxarifado));
            Almoxarife resultado = service.cadastrarAlmoxarife(request);
            assertNotNull(resultado);
            assertEquals(almoxarife.getNome(),resultado.getNome());
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Request Inválido")
        void deveLancarExceptionRequestInvalido(){
            assertThrows(EntidadeInvalidException.class,()->
                    service.cadastrarAlmoxarife(null));
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Email Duplicado")
        void deveLancarExceptionEmailDuplicado(){
            AlmoxarifeRequest request = new AlmoxarifeRequest(
                    "Gabriel Furtado","gabriel@gmail.com","1140028922","40028922",1);
            when(almoxarifePort.existsByEmailAndAlmoxarifadoId(request.email(),request.idAlmoxarifado())).thenReturn(true);
            assertThrows(EntidadeConflictException.class,()->
                    service.cadastrarAlmoxarife(request));
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Almoxarifado Não Encontrado")
        void deveLancarExceptionAlmoxarifadoNaoEncontrado(){
            AlmoxarifeRequest request = new AlmoxarifeRequest(
                    "Gabriel Furtado","gabriel@gmail.com","1140028922","40028922",1);
            when(almoxarifadoPort.findById(request.idAlmoxarifado())).thenReturn(Optional.empty());
            assertThrows(EntidadeInvalidException.class,()->
                    service.cadastrarAlmoxarife(request));
        }
    }

    @Nested
    @DisplayName("Testes do Método Listar")
    class MetodoListar{
        @Test
        @DisplayName("Deve Listar Almoxarifes Corretamente")
        void deveListarAlmoxarifes(){
            Almoxarife almoxarife = new Almoxarife();
            almoxarife.setNome("Gabriel Furtado");
            when(almoxarifePort.findAll()).thenReturn(List.of(almoxarife));
            List<Almoxarife> resultado = service.listarAlmoxarifes();
            assertNotNull(resultado);
            assertEquals(almoxarife.getNome(),resultado.getFirst().getNome());
        }

        @Test
        @DisplayName("Deve Retornar Lista Vazia")
        void deveRetornarListaVazia(){
            when(almoxarifePort.findAll()).thenReturn(List.of());
            List<Almoxarife> resultado = service.listarAlmoxarifes();
            assertNotNull(resultado);
            assertEquals(List.of(),resultado);
        }
    }

    @Nested
    @DisplayName("Testes do Método Excluir")
    class MetodoExcluir{
        @Test
        @DisplayName("Deve Excluir Almoxarife Corretamente")
        void deveExcluirAlmoxarife(){
            Almoxarife almoxarife = new Almoxarife();
            almoxarife.setId(1);
            when(almoxarifePort.findById(anyInt())).thenReturn(Optional.of(almoxarife));
            service.excluirAlmoxarife(almoxarife.getId());
            verify(almoxarifePort,times(1)).delete(almoxarife);
        }

        @Test
        @DisplayName("Deve Lançar Exception Por Almoxarife Não Encontrado")
        void deveLancarExceptionAlmoxarifeNaoEncontrado(){
            when(almoxarifePort.findById(anyInt())).thenReturn(Optional.empty());
            assertThrows(EntidadeNaoExisteException.class,()->
                    service.excluirAlmoxarife(anyInt()));
        }
    }

    @Nested
    @DisplayName("Testes do Método Autenticar")
    class MetodoAutenticar{
        @Test
        @DisplayName("Deve Autenticar Com Sucesso")
        void deveAutenticar(){
            Almoxarife teste = new Almoxarife();
            teste.setEmail("gabriel@gmail.com");
            teste.setSenha("123456");
            Almoxarifado almoxarifado = new Almoxarifado();
            almoxarifado.setId(1);
            Almoxarife almoxarife = new Almoxarife();
            almoxarife.setEmail("gabriel@gmail.com");
            almoxarife.setAlmoxarifado(almoxarifado);
            Authentication authentication = mock(Authentication.class);
            when(authenticationManager.authenticate(any())).thenReturn(authentication);
            when(almoxarifePort.findByEmail(anyString())).thenReturn(Optional.of(almoxarife));
            when(gerenciadorTokenJwt.generateToken(authentication)).thenReturn("token-fake");
            AlmoxarifeToken resultado = service.autenticar(teste);
            assertNotNull(resultado);
            assertEquals("token-fake",resultado.getToken());
            verify(authenticationManager,times(1)).authenticate(any());
            verify(almoxarifePort,times(1)).findByEmail("gabriel@gmail.com");
        }

        @Test
        @DisplayName("Deve Lançar Exception Para Email Não Encontrado")
        void deveLancarExceptionEmailNaoExiste() {
            Almoxarife request = new Almoxarife();
            request.setEmail("naoexiste@gmail.com");
            request.setSenha("123456");
            Authentication authentication = mock(Authentication.class);
            when(authenticationManager.authenticate(any())).thenReturn(authentication);
            when(almoxarifePort.findByEmail("naoexiste@gmail.com")).thenReturn(Optional.empty());
            assertThrows(ResponseStatusException.class, () ->
                    service.autenticar(request));
            verify(almoxarifePort, never()).delete(any());
        }

        @Test
        @DisplayName("Deve Lançar Exception Para Autenticação Falha")
        void deveLancarExceptionQuandoSenhaIncorreta() {
            Almoxarife request = new Almoxarife();
            request.setEmail("gabriel@gmail.com");
            request.setSenha("senhaErrada");
            when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Credenciais inválidas"));
            assertThrows(RuntimeException.class, () ->
                    service.autenticar(request));
            verify(almoxarifePort, never()).findByEmail(any());
        }

        @Test
        @DisplayName("Deve Lançar Exception Para Email Nulo")
        void deveLancarExceptionQuandoEmailNulo() {
            Almoxarife request = new Almoxarife();
            request.setEmail(null);
            request.setSenha("123456");
            when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException());
            assertThrows(RuntimeException.class, () ->
                    service.autenticar(request));
        }
    }

    @Nested
    @DisplayName("Testes para Método Atualizar")
    class MetodoAtualizar{
        @Test
        @DisplayName("Deve Atualizar Almoxarife Corretamente")
        void deveAtualizarAlmoxarife(){
            AlmoxarifeUpdateRequest request = new AlmoxarifeUpdateRequest("Gabriel Furtado","gabriel@gmail.com","1140028922",1);
            Almoxarife almoxarife = new Almoxarife();
            Integer id = 1;
            Almoxarifado almoxarifado = new Almoxarifado();
            almoxarifado.setId(1);
            when(almoxarifadoPort.findById(1)).thenReturn(Optional.of(almoxarifado));
            when(almoxarifePort.findById(id)).thenReturn(Optional.of(almoxarife));
            when(almoxarifePort.save(almoxarife)).thenReturn(almoxarife);
            AlmoxarifeResponse resultado = service.atualizarParcial(id,request);
            assertNotNull(resultado);
            assertEquals(almoxarife.getId(),resultado.id());
        }

        @Test
        @DisplayName("Deve Lançar Exception para Almoxarifado Inválido")
        void deveLancarExceptionAlmoxarifadoInvalido(){
            Almoxarife almoxarife = new Almoxarife();
            almoxarife.setId(1);
            AlmoxarifeUpdateRequest request = new AlmoxarifeUpdateRequest(
                    "Gabriel Furtado","gabriel@gmail.com","1140028922",1);
            when(almoxarifePort.findById(1)).thenReturn(Optional.of(almoxarife));
            when(almoxarifadoPort.findById(anyInt())).thenThrow(new EntidadeInvalidException("Almoxarifado Não Encontrado"));
            assertThrows(EntidadeInvalidException.class,()->
                    service.atualizarParcial(1,request));
        }

        @Test
        @DisplayName("Deve Lançar Exception para Almoxarife Inválido")
        void deveLancarExceptionAlmoxarifeInvalido(){
            AlmoxarifeUpdateRequest request = new AlmoxarifeUpdateRequest(
                    "Gabriel Furtado","gabriel@gmail.com","1140028922",1);
            when(almoxarifePort.findById(1)).thenReturn(Optional.empty());
            assertThrows(EntidadeInvalidException.class,()->
                    service.atualizarParcial(1,request));
        }

        @Test
        @DisplayName("Deve Lançar Exception para Request Nulo")
        void deveLancarExceptionRequestNulo(){
            assertThrows(EntidadeInvalidException.class,()->
                    service.atualizarParcial(1,null));
        }

        @Test
        @DisplayName("Não Deve Alterar Campos Para Request Com Valores Nulos")
        void deveNaoAtualizarCamposNulos() {
            Almoxarife almoxarife = new Almoxarife();
            almoxarife.setNome("Gabriel Furtado");
            AlmoxarifeUpdateRequest request = new AlmoxarifeUpdateRequest(null, null, null, null);
            when(almoxarifePort.findById(1)).thenReturn(Optional.of(almoxarife));
            when(almoxarifePort.save(any())).thenReturn(almoxarife);
            AlmoxarifeResponse response = service.atualizarParcial(1, request);
            assertEquals("Gabriel Furtado", almoxarife.getNome());
        }
    }
}