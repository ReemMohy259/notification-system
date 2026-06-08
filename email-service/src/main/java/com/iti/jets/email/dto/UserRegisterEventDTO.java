package com.iti.jets.email.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
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
