package org.example.ictdepartmentmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String batchName;

    private Integer intakeYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Month intakeMonth;

    private Integer studentCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
