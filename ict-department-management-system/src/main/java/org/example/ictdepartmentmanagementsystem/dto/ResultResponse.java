package org.example.ictdepartmentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ictdepartmentmanagementsystem.entity.Grade;
import org.example.ictdepartmentmanagementsystem.entity.Semester;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {

    private UserResponse student;
    private SubjectResponse subject;
    private double marks;
    private Grade grade;
    private double gradePoint;
    private Semester semester;
}
