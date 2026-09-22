package org.example.ictdepartmentmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "academicstaff")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AcademicStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private Honorific honorific;
    private String email;
    private int phoneNumber;
    private List<String> positions;

    @Enumerated(EnumType.STRING)
    private Title title;
    private List<String> qualifications;
    private List<String> researchInterests;
    private String picture;
}
