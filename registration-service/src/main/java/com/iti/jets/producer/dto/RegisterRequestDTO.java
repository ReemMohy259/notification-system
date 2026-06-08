package com.iti.jets.producer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 14, message = "First name length is between 3 and 14")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 14, message = "Last name length is between 3 and 14")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
