package school.sptech.sistema_estoque.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitMqProperties.class)
public class RabbitMqConfig {

    private final RabbitMqProperties properties;

    public RabbitMqConfig(RabbitMqProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DirectExchange getDirectExchange() {
        return new DirectExchange(properties.exchangeName());
    }

    @Bean
    public Queue getQueue() {
        return QueueBuilder
            .durable(properties.queueName())
            .build();
    }

    @Bean
    public Binding getBinding(
            Queue queue,
            DirectExchange directExchange
    ) {
        return BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(properties.queueName());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
