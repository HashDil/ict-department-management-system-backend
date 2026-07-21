package org.example.ictdepartmentmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.ictdepartmentmanagementsystem.entity.Month;

@Data
public class AddBatchRequest {

    @NotBlank(message = "Intake year is required")
    private Integer intakeYear;

    @NotBlank(message = "Intake month is required")
    private Month intakeMonth;

    @NotBlank(message = "Student count is required")
    private Integer studentCount;

    @NotBlank(message = "Status is required")
    private String status;
}
