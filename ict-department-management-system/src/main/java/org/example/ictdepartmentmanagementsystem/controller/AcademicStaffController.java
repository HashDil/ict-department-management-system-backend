package org.example.ictdepartmentmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.ictdepartmentmanagementsystem.dto.AcademicStaffResponse;
import org.example.ictdepartmentmanagementsystem.dto.AddAcademicStaffRequest;
import org.example.ictdepartmentmanagementsystem.dto.UpdateAcademicStaffRequest;
import org.example.ictdepartmentmanagementsystem.service.AcademicStaffService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/academicStaff")
public class AcademicStaffController {

    private final AcademicStaffService academicStaffService;

    public AcademicStaffController(AcademicStaffService academicStaffService) {
        this.academicStaffService = academicStaffService;
    }

    @GetMapping
    public ResponseEntity<List<AcademicStaffResponse>> getAllAcademicStaff(){
        return ResponseEntity.ok(academicStaffService.getAllAcademicStaff());
    }

    @PostMapping(value = "/addAcademicStaff")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addAcademicStaff(@Valid @RequestBody AddAcademicStaffRequest request) {
        return ResponseEntity.ok(academicStaffService.addAcademicStaff(request));
    }

    @PutMapping("/updateAcademicStaff/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateAcademicStaff(@Valid @RequestBody UpdateAcademicStaffRequest request, @PathVariable String email){
        return ResponseEntity.ok(academicStaffService.updateAcademicStaff(email, request));
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteAcademicStaff(@PathVariable String email) throws IOException {
        return ResponseEntity.ok(academicStaffService.deleteAcademicStaff(email));
    }

    @PostMapping("/addPicture/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addPicture(@RequestParam("file") MultipartFile file, @PathVariable String email) throws IOException {
        return ResponseEntity.ok(academicStaffService.addPictureToAcademicStaff(file,email));
    }

    @DeleteMapping("/deletePicture/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deletePicture(@PathVariable String email) throws IOException {
        return ResponseEntity.ok(academicStaffService.deletePicture(email));
    }
}
