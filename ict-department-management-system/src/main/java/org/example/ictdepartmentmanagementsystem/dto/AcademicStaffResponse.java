package org.example.ictdepartmentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AcademicStaffResponse {
    private String displayName;
    private String email;
    private int phoneNumber;
    private String title;
    private List<String> positions;
    private List<String> qualifications;
    private List<String> researchInterests;
    private String picture;
}
