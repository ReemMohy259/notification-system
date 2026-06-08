package com.iti.jets.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class UserRegisterEventDTO {

    private UUID userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;
}
