package com.iti.jets.email.controller;

import com.iti.jets.email.dto.UserRegisterEventDTO;
import com.iti.jets.email.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final EmailService service;

    public NotificationController(EmailService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserRegisterEventDTO> all() {
        return service.getAll();
    }

    @GetMapping("/latest")
    public UserRegisterEventDTO latest() {
        return service.getLatest();
    }
}