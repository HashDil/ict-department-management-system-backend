package org.example.ictdepartmentmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Enrollment number is required")
    private String enrollmentNumber;

    @NotBlank(message = "Password is required")
    private String password;
}
