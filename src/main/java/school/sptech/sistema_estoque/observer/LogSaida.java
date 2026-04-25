package school.sptech.sistema_estoque.observer;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogSaida implements MovimentacaoObserver{

    private static final Logger logger = LoggerFactory.getLogger(LogSaida.class);

    @Override
    public void gerarLogs(String mensagem) {
        logger.info("[SAIDA] {}", mensagem);
    }

    @Override
    public void atualizar(String mensagem) {
        logger.debug("[PAINEL - SAIDA] {}", mensagem);
    }
}
