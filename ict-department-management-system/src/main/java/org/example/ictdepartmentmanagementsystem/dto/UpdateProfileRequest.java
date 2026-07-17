package org.example.ictdepartmentmanagementsystem.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateProfileRequest {

    @NotBlank(message = "Enrollment number is required")
    @Pattern(
            regexp = "^UWU/ICT/\\d{2}/\\d{3}$",
            message = "Enrollment number must be in format UWU/ICT/YY/NNN (e.g. UWU/ICT/23/019)"
    )
    private String enrollmentNumber;

    @NotBlank(message="Full name is required")
    private String fullName;

    @NotBlank(message = "Name with initials is required")
    private String nameWithInitials;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
}
