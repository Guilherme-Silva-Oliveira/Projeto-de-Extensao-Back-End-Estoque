package school.sptech.sistema_estoque.service;

import org.springframework.stereotype.Service;
import school.sptech.sistema_estoque.dto.mq.AlertaMessage;

@Service
public class AlertasService {

    public void processarAlerta(AlertaMessage mensagem) {
        System.out.println("Alerta Recebido"+mensagem);
    }
}
