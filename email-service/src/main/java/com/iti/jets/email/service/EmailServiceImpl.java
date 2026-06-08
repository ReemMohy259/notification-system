package com.iti.jets.email.service;

import com.iti.jets.email.dto.UserRegisterEventDTO;
import com.iti.jets.email.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;

    public EmailServiceImpl(EmailRepository repository) {
        this.repository = repository;
    }

    @Override
    public void sendWelcomeEmail(UserRegisterEventDTO event) {

        // Simulating email sending
        System.out.println("=================================");
        System.out.println("TO: " + event.getEmail());
        System.out.println("SUBJECT: Welcome " + event.getFirstName());
        System.out.println("BODY:");
        System.out.println("Hello " + event.getFirstName() + ", welcome to our system!");
        System.out.println("=================================");
    }

    @Override
    public UserRegisterEventDTO getLatest() {
        return repository.findLatest();
    }

    @Override
    public List<UserRegisterEventDTO> getAll() {
        return repository.findAll();
    }
}