package org.example.ictdepartmentmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.ictdepartmentmanagementsystem.dto.AddSubjectRequest;
import org.example.ictdepartmentmanagementsystem.dto.SubjectResponse;
import org.example.ictdepartmentmanagementsystem.entity.Semester;
import org.example.ictdepartmentmanagementsystem.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectResponse> addSubject(@Valid @RequestBody AddSubjectRequest addSubjectRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.addSubject(addSubjectRequest));
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<List<SubjectResponse>> getAllSubjectsBySemester(@PathVariable Semester semester) {
        return ResponseEntity.ok(subjectService.getSubjectsBySemester(semester));
    }
}
