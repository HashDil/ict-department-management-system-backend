package org.example.ictdepartmentmanagementsystem.dto;

import lombok.Data;
import org.example.ictdepartmentmanagementsystem.entity.Grade;
import org.example.ictdepartmentmanagementsystem.entity.Semester;

@Data
public class ResultResponse {

    private String subjectCode;
    private String subjectName;
    private int creditHours;
    private double marks;
    private String grade;
    private double gradePoint;
    private String semester;
}
