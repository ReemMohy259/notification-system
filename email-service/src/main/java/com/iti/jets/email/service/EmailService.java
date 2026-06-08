package com.iti.jets.email.service;

import com.iti.jets.email.dto.UserRegisterEventDTO;

import java.util.List;

public interface EmailService {
    void sendWelcomeEmail(UserRegisterEventDTO event);

    UserRegisterEventDTO getLatest();

    List<UserRegisterEventDTO> getAll();
}
