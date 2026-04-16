package school.sptech.sistema_estoque.observer;

import org.springframework.stereotype.Component;

@Component
public class LogSaida implements MovimentacaoObserver{

    @Override
    public void gerarLogs(String mensagem) {
        System.out.println("Log de Saída: " + mensagem);
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("Atualizando painel: Saída - " + mensagem);
    }
}
