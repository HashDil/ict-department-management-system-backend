package org.example.ictdepartmentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ictdepartmentmanagementsystem.entity.Title;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAcademicStaffRequest {
    private String name;
    private String honorific;
    private int phoneNumber;
    private List<String> positions;
    private Title title;
    private List<String> qualifications;
    private List<String> researchInterests;
}
