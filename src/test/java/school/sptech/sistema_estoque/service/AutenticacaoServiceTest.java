package school.sptech.sistema_estoque.service;

import io.swagger.v3.oas.annotations.links.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.port.AlmoxarifePort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {
    @Mock
    private AlmoxarifePort almoxarifePort;

    @InjectMocks
    private AutenticacaoService service;

    @Test
    @DisplayName("Deve Carregar Usuário Com Sucesso")
    void deveCarregarUsuario(){
        Almoxarife almoxarife = new Almoxarife();
        almoxarife.setNome("Gabriel Furtado");
        almoxarife.setEmail("gabriel@gmail.com");
        almoxarife.setSenha("123456");
        when(almoxarifePort.findByEmail(anyString())).thenReturn(Optional.of(almoxarife));
        UserDetails resultado = service.loadUserByUsername(almoxarife.getNome());
        assertNotNull(resultado);
        assertEquals(almoxarife.getEmail(),resultado.getUsername());
    }

    @Test
    @DisplayName("Deve Lançar Exception Para Usuário Não Encontrado")
    void deveLancarExceptionUsuarioNaoEncontrado(){
        when(almoxarifePort.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,()->
                service.loadUserByUsername(anyString()));
    }
}