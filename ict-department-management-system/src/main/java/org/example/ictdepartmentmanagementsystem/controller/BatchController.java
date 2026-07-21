package org.example.ictdepartmentmanagementsystem.controller;

import org.example.ictdepartmentmanagementsystem.dto.AddBatchRequest;
import org.example.ictdepartmentmanagementsystem.dto.BatchResponse;
import org.example.ictdepartmentmanagementsystem.service.BatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BatchResponse> addBatch(@RequestBody AddBatchRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(batchService.addBatch(request));
    }

    @GetMapping
    public ResponseEntity<List<BatchResponse>> getAllBatches(){
        return  ResponseEntity.ok(batchService.getAllBatches());
    }

    @GetMapping("/{batchName}")
    public ResponseEntity<BatchResponse> getBatch(@PathVariable("batchName") String batchName){
        return  ResponseEntity.ok(batchService.getBatch(batchName));
    }

    @PutMapping("/{batchName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BatchResponse>updateBatch(@PathVariable String batchName, @RequestBody AddBatchRequest request){
        return  ResponseEntity.ok(batchService.updateBatch(batchName, request));
    }

    @DeleteMapping("/{batchName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>>deleteBatch(@PathVariable("batchName") String batchName){
        batchService.deleteBatch(batchName);
        return  ResponseEntity.ok(Map.of("message", "Batch has been deleted"));
    }

}
