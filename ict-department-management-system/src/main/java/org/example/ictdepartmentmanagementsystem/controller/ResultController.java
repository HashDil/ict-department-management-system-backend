package org.example.ictdepartmentmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.ictdepartmentmanagementsystem.dto.AddResultRequest;
import org.example.ictdepartmentmanagementsystem.dto.ResultResponse;
import org.example.ictdepartmentmanagementsystem.entity.Semester;
import org.example.ictdepartmentmanagementsystem.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResultResponse> addResult(@Valid @RequestBody AddResultRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(resultService.addResult(request));
    }

    @GetMapping("/my-results/{semester}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<ResultResponse>> getMyResultsBySemester(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Semester semester){
        return ResponseEntity.ok(resultService.getStudentResultsBySemester(userDetails.getUsername(), semester));
    }

    @GetMapping("/my-results")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<ResultResponse>> getMyResults(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(resultService.getStudentResults(userDetails.getUsername()));
    }

    // CHANGED TO @PostMapping
    @PostMapping("/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ResultResponse>> getStudentResults(@RequestBody Map<String, String> request){
        String enrollmentNumber = request.get("enrollmentNumber");
        return ResponseEntity.ok(resultService.getStudentResults(enrollmentNumber));
    }

    // CHANGED TO @PostMapping
    @PostMapping("/student/{semester}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ResultResponse>> getStudentResultsBySemester(@RequestBody Map<String, String> request, @PathVariable Semester semester){
        String enrollmentNumber = request.get("enrollmentNumber");
        return ResponseEntity.ok(resultService.getStudentResultsBySemester(enrollmentNumber,semester));
    }

    @DeleteMapping("/{subjectCode}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String,String>> deleteResult(@PathVariable String subjectCode, @RequestBody Map<String, String> request){
        String enrollmentNumber = request.get("enrollmentNumber");

        String result = resultService.deleteResult(subjectCode, enrollmentNumber);
        return ResponseEntity.ok(Map.of(result,"result deleted successfully"));
    }
}