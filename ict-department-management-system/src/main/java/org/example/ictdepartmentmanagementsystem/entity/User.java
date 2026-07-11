package org.example.ictdepartmentmanagementsystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="users")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "enrollment_number", nullable = false, unique = true)
    private String enrollmentNumber;

    private String fullName;
    private String nameWithInitials;

    @Email
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
