package org.example.ictdepartmentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {

    private String subjectCode;
    private String subjectName;
    private int creditHours;
    private String semester;
}
