package org.example.ictdepartmentmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.ictdepartmentmanagementsystem.entity.Grade;
import org.example.ictdepartmentmanagementsystem.entity.Semester;

@Data
public class AddResultRequest {

    @NotBlank(message = "Enrollment number is required")
    private String enrollmentNumber;

    @NotNull(message = "Subject id is required")
    private Long subjectId;

    @NotNull(message = "Marks is required")
    private double marks;

    @NotNull(message = "Grade is required")
    private Grade grade;

    @NotNull(message = "Semester is required")
    private Semester semester;
}
