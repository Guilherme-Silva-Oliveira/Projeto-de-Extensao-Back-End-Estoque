package school.sptech.sistema_estoque.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacaoEntryPoint implements AuthenticationEntryPoint {

    
    // trata erros de autenticacao e defire status da resposta
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof BadCredentialsException ||
                authException instanceof InsufficientAuthenticationException) {
            // 401
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            // 403
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
