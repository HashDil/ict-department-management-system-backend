package org.example.ictdepartmentmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.example.ictdepartmentmanagementsystem.dto.AdminRegisterStudentRequest;
import org.example.ictdepartmentmanagementsystem.entity.Batch;
import org.example.ictdepartmentmanagementsystem.entity.Role;
import org.example.ictdepartmentmanagementsystem.entity.Student;
import org.example.ictdepartmentmanagementsystem.repository.BatchRepository;
import org.example.ictdepartmentmanagementsystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final BatchRepository batchRepository;

    public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, BatchRepository batchRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.batchRepository = batchRepository;
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

        Batch batch = batchRepository.findByBatchName(request.getBatchName());
        if (batch == null){
            throw new IllegalArgumentException("Batch not found");
        }

        student.setBatch(batch);

        userRepository.save(student);

        emailService.sendDefaultCredentials(request.getFullName(),request.getEmail(),request.getEnrollmentNumber(),defaultPassword);
    }

    private String generateDefaultPassword() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "UWU@"+uuid;
    }

    @Transactional
    public void deleteStudent(String enrollmentNumber){
        if(!userRepository.existsByEnrollmentNumber(enrollmentNumber)){
            throw new IllegalArgumentException("User does not exist");
        }
        userRepository.deleteByEnrollmentNumber(enrollmentNumber);
    }
}
