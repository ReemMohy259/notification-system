package com.iti.jets.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "user.exchange";

    public static final String NOTIFICATION_QUEUE = "notification.queue";

    public static final String AUDIT_QUEUE = "audit.queue";

    public static final String ROUTING_KEY = "user.registered";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return QueueBuilder.durable(NOTIFICATION_QUEUE).build();
    }

    @Bean
    public Queue auditQueue() {
        return QueueBuilder.durable(AUDIT_QUEUE).build();
    }

    @Bean
    public Binding notificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(exchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public Binding auditBinding() {
        return BindingBuilder
                .bind(auditQueue())
                .to(exchange())
                .with(ROUTING_KEY);
    }


    // JSON Message Converter
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }
}