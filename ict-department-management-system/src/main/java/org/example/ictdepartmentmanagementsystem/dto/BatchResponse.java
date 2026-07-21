package org.example.ictdepartmentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchResponse {
    private String batchName;
    private Integer intakeYear;
    private String intakeMonth;
    private Integer studentCount;
    private String status;
}
