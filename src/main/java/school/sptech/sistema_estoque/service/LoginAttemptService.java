package school.sptech.sistema_estoque.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    public static final int MAX_ATTEMPTS = 5;
    private static final int DURACAO_BLOQUEIO_MINUTOSS = 1;

    private final Cache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        this.attemptsCache = Caffeine.newBuilder()
                .expireAfterWrite(DURACAO_BLOQUEIO_MINUTOSS, TimeUnit.MINUTES)
                // Limite defensivo: armazena no máximo 1000 e-mails no cache para não sobrecarregar a JVM
                .maximumSize(1000)
                .build();
    }

    /**
     * Chamado quando o usuário acerta a senha.
     * Invalida (remove) o e-mail do cache para zerar as tentativas falhas anteriores.
     */
    public void loginSucceeded(String key) {
        if (key != null) {
            attemptsCache.invalidate(key);
        }
    }

    /**
     * Chamado quando o usuário erra as credenciais.
     * Incrementa o contador de erros daquele e-mail no cache.
     */
    public void loginFailed(String key) {
        if (key == null) return;
        Integer attempts = attemptsCache.getIfPresent(key);
        if (attempts == null) {
            attempts = 0;
        }
        attemptsCache.put(key, attempts + 1);
    }

    /**
     * Consulta se o e-mail atingiu o limite de 5 erros.
     * Retorna true se estiver bloqueado, impedindo que a tentativa chegue ao AuthenticationManager.
     */
    public boolean isBlocked(String key) {
        if (key == null) return false;
        Integer attempts = attemptsCache.getIfPresent(key);
        return attempts != null && attempts >= MAX_ATTEMPTS;
    }
}