package org.example.ictdepartmentmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.ictdepartmentmanagementsystem.dto.AdminRegisterStudentRequest;
import org.example.ictdepartmentmanagementsystem.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
}
