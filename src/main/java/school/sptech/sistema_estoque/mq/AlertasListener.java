package school.sptech.sistema_estoque.mq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.dto.mq.AlertaMessage;
import school.sptech.sistema_estoque.service.AlertasService;

@Component
@AllArgsConstructor
public class AlertasListener {
    private final AlertasService alertasService;

    @RabbitListener(queues = "${app.rabbitmq.queue-name}", messageConverter = "messageConverter")
    public void receberMensagem(AlertaMessage message) {
        alertasService.processarAlerta(message);
    }
}
