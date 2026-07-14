package org.example.ictdepartmentmanagementsystem.service;

import org.example.ictdepartmentmanagementsystem.dto.AdminRegisterStudentRequest;
import org.example.ictdepartmentmanagementsystem.entity.Role;
import org.example.ictdepartmentmanagementsystem.entity.Student;
import org.example.ictdepartmentmanagementsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public void registerStudent(AdminRegisterStudentRequest request){
        if(userRepository.existsByEnrollmentNumber(request.getEnrollmentNumber())){
            throw new IllegalArgumentException("Enrollment number already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        String defaultPassword = generateDefaultPassword();

        Student student = new Student();
        student.setEnrollmentNumber(request.getEnrollmentNumber());
        student.setEmail(request.getEmail());
        student.setFullName(request.getFullName());
        student.setNameWithInitials(request.getNameWithInitials());
        student.setPassword(passwordEncoder.encode(defaultPassword));
        student.setRole(Role.STUDENT);
        student.setBatch(request.getBatch());

        userRepository.save(student);

        emailService.sendDefaultCredentials(request.getFullName(),request.getEmail(),request.getEnrollmentNumber(),defaultPassword);
    }

    private String generateDefaultPassword() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "UWU@"+uuid;
    }
}
