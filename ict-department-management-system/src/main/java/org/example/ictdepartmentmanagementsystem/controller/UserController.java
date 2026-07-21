package org.example.ictdepartmentmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.ictdepartmentmanagementsystem.dto.ChangePasswordRequest;
import org.example.ictdepartmentmanagementsystem.dto.UpdateProfileRequest;
import org.example.ictdepartmentmanagementsystem.dto.UserResponse;
import org.example.ictdepartmentmanagementsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin (origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(@AuthenticationPrincipal UserDetails userDetails) {

        UserResponse response = userService.getUserByEnrollmentNumber(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ChangePasswordRequest request) {

        userService.changePassword(userDetails.getUsername(), request);
        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }

    @PutMapping("/update-profile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse>updateUserDetails(@Valid @RequestBody UpdateProfileRequest request) {
        UserResponse updated = userService.updateProfile(request);
        return ResponseEntity.ok(updated);
    }
}
