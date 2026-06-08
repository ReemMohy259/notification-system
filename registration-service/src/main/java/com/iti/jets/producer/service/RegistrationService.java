package com.iti.jets.producer.service;

import com.iti.jets.producer.dto.RegisterRequestDTO;
import com.iti.jets.producer.dto.RegisterResponseDTO;

public interface RegistrationService {

    RegisterResponseDTO registerUser(RegisterRequestDTO request);
}
