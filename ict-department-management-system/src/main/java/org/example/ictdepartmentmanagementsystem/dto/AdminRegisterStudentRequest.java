package org.example.ictdepartmentmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.example.ictdepartmentmanagementsystem.entity.Batch;

@Data
public class AdminRegisterStudentRequest {

    @NotBlank(message = "Enrollment number is required")
    @Pattern(
            regexp = "^UWU/ICT/\\d{2}/\\d{3}$",
            message = "Enrollment number must be in format UWU/ICT/YY/NNN (e.g. UWU/ICT/23/019)"
    )
    private String enrollmentNumber;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Name with initials is required")
    private String naneWithInitials;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Batch is required")
    private Batch batch;

}
