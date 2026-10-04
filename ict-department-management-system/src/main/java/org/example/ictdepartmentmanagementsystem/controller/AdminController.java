package org.example.ictdepartmentmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.ictdepartmentmanagementsystem.dto.AdminRegisterStudentRequest;
import org.example.ictdepartmentmanagementsystem.dto.UpdateProfileRequest;
import org.example.ictdepartmentmanagementsystem.dto.UserResponse;
import org.example.ictdepartmentmanagementsystem.service.AdminService;
import org.example.ictdepartmentmanagementsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
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

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(adminService.getAllStudents());
    }

    @PutMapping("/update-profile")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponse>updateUserDetails(@Valid @RequestBody UpdateProfileRequest request) {
        UserResponse updated = userService.updateProfile(request);
        return ResponseEntity.ok(updated);
    }

}
