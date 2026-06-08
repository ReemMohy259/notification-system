package com.iti.jets.producer.service;

import com.iti.jets.producer.dto.RegisterRequestDTO;
import com.iti.jets.producer.dto.RegisterResponseDTO;
import com.iti.jets.producer.dto.UserRegisterEventDTO;
import com.iti.jets.producer.entity.User;
import com.iti.jets.producer.exception.EmailAlreadyExistsException;
import com.iti.jets.producer.publisher.UserEventPublisher;
import com.iti.jets.producer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository repository;
    private final UserEventPublisher publisher;

    public RegistrationServiceImpl(
            UserRepository repository,
            UserEventPublisher publisher) {

        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

        User savedUser = repository.save(user);

        UserRegisterEventDTO event = UserRegisterEventDTO.builder()
                .userId(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .registeredAt(savedUser.getCreatedAt())
                .build();

        publisher.publish(event);

        return new RegisterResponseDTO("User registered successfully");
    }
}
