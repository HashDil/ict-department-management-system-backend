package org.example.ictdepartmentmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.ictdepartmentmanagementsystem.entity.Semester;

@Data
public class AddSubjectRequest {

    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @NotNull(message = "Credit hours is required")
    private int creditHours;

    @NotNull(message = "Semester is required")
    private Semester semester;
}
