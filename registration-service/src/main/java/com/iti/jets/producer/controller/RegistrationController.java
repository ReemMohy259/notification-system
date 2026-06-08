package com.iti.jets.producer.controller;

import com.iti.jets.producer.dto.RegisterRequestDTO;
import com.iti.jets.producer.dto.RegisterResponseDTO;
import com.iti.jets.producer.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(
            RegistrationService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDTO register(
            @Valid @RequestBody RegisterRequestDTO request) {

        return service.registerUser(request);
    }
}