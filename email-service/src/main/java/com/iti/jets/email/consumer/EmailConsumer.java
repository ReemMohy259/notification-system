package com.iti.jets.email.consumer;

import com.iti.jets.email.dto.UserRegisterEventDTO;
import com.iti.jets.email.repository.EmailRepository;
import com.iti.jets.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailRepository repository;
    private final EmailService service;

    public EmailConsumer(EmailRepository repository, EmailService service) {
        this.repository = repository;
        this.service = service;
    }

    @RabbitListener(queues = "notification.queue")
    public void receive(UserRegisterEventDTO event) {

        System.out.println("Received : " + event.getEmail());

        service.sendWelcomeEmail(event);
        repository.save(event);
    }
}