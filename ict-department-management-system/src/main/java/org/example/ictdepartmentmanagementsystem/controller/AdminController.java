package org.example.ictdepartmentmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.ictdepartmentmanagementsystem.dto.AdminRegisterStudentRequest;
import org.example.ictdepartmentmanagementsystem.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register-student")
    public ResponseEntity<Map<String, String>> registerStudent(@Valid @RequestBody AdminRegisterStudentRequest request){
        adminService.registerStudent(request);

        return ResponseEntity.ok(Map.of("message", "Student registered successfully."+"Login credentials have been sent to "+request.getEmail()));
    }

    @DeleteMapping("/delete-student")
    public ResponseEntity<Map<String, String>> deleteStudent(@Valid @RequestBody Map<String, String> request){
        String enrollmentNumber = request.get("enrollmentNumber");
        adminService.deleteStudent(enrollmentNumber);
        return ResponseEntity.ok(Map.of("message", "Student deleted successfully."));
    }
}
