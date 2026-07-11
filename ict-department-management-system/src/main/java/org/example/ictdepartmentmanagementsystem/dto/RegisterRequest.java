package org.example.ictdepartmentmanagementsystem.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Enrollment number is required")
    private String enrollmentNumber;

    @NotBlank(message="Full name is required")
    private String fullName;

    @NotBlank(message="Name with initials is required")
    private String nameWithInitials;

    @Email(message = "Email should be valid")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8, message = "Password must be at least 8 charachters")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;
}
