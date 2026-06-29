package org.example.ictdepartmentmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Batch batch;
}
