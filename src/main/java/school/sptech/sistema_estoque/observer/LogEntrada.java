package school.sptech.sistema_estoque.observer;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Primary
public class LogEntrada implements MovimentacaoObserver{

    private static final Logger logger = LoggerFactory.getLogger(LogEntrada.class);

    @Override
    public void gerarLogs(String mensagem) {
        logger.info("[ENTRADA] {}", mensagem);
    }

    @Override
    public void atualizar(String mensagem) {
        logger.debug("[PAINEL - ENTRADA] {}", mensagem);
    }
}
