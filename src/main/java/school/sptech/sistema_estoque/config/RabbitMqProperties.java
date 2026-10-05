package school.sptech.sistema_estoque.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rabbitmq")
public record RabbitMqProperties(
    String exchangeName,
    String queueName,
    String routingKeyName
) {}
