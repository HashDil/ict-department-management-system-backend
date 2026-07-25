package org.example.ictdepartmentmanagementsystem.controller;

import org.example.ictdepartmentmanagementsystem.service.ProfilePictureService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/profile-picture")
public class ProfilePictureController {

    private final ProfilePictureService profilePictureService;

    public ProfilePictureController(ProfilePictureService profilePictureService) {
        this.profilePictureService = profilePictureService;
    }

    @PostMapping(value = "/upload/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> uploadStudentProfilePicture(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(
                profilePictureService.uploadProfilePicture(userDetails.getUsername(), file)
        );
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Map<String,String>>uploadMyProfilePicture(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(
                profilePictureService.uploadProfilePicture(userDetails.getUsername(), file));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Map<String, String>> deleteMyProfilePicture(@AuthenticationPrincipal UserDetails userDetails) throws IOException {

        profilePictureService.deleteProfilePicture(userDetails.getUsername());
        return ResponseEntity.ok(Map.of("message", "Profile Picture deleted successfully"));
    }

    @DeleteMapping("/delete/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteStudentProfilePicture(@RequestBody Map<String,String> request) throws IOException {
        profilePictureService.deleteProfilePicture(request.get("enrollmentNumber"));
        return ResponseEntity.ok(Map.of("message", "Profile Picture deleted successfully"));
    }
}
