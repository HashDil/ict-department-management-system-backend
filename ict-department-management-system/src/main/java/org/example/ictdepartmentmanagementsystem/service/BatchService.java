package org.example.ictdepartmentmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.example.ictdepartmentmanagementsystem.dto.AddBatchRequest;
import org.example.ictdepartmentmanagementsystem.dto.BatchResponse;
import org.example.ictdepartmentmanagementsystem.entity.Batch;
import org.example.ictdepartmentmanagementsystem.entity.Status;
import org.example.ictdepartmentmanagementsystem.repository.BatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService {

    private final BatchRepository batchRepository;

    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public BatchResponse addBatch(AddBatchRequest addBatchRequest) {
        String batchName ="BATCH_" +addBatchRequest.getIntakeYear();
        if (batchRepository.existsBatchByBatchName(batchName)){
            throw new IllegalArgumentException("Batch already exists");
        }
        Batch batch = new Batch();
        batch.setBatchName(batchName);
        batch.setIntakeYear(addBatchRequest.getIntakeYear());
        batch.setIntakeMonth(addBatchRequest.getIntakeMonth());
        batch.setStudentCount(addBatchRequest.getStudentCount());
        batch.setStatus(Status.valueOf(addBatchRequest.getStatus().trim().toUpperCase()));

        return mapToResponse(batchRepository.save(batch));
    }

    public List<BatchResponse> getAllBatches(){
        return batchRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public BatchResponse getBatch(String batchName){
        try {
            return mapToResponse(batchRepository.findByBatchName(batchName));
        }catch (Exception e){
            throw new IllegalArgumentException("Batch not found");
        }
    }

    public BatchResponse updateBatch(String batchName, AddBatchRequest request){
        Batch batch = batchRepository.findByBatchName(batchName);
        if (batch == null){
            throw new IllegalArgumentException("Batch not found");
        }
        batch.setBatchName("BATCH_"+request.getIntakeYear());
        batch.setIntakeYear(request.getIntakeYear());
        batch.setIntakeMonth(request.getIntakeMonth());
        batch.setStudentCount(request.getStudentCount());
        batch.setStatus(Status.valueOf(request.getStatus().trim().toUpperCase()));

        return mapToResponse(batchRepository.save(batch));
    }

    @Transactional
    public void deleteBatch(String batchName){
        if (!batchRepository.existsBatchByBatchName(batchName)){
            throw new IllegalArgumentException("Batch not found");
        }
        batchRepository.deleteByBatchName(batchName);
    }

    public BatchResponse mapToResponse(Batch batch) {
        return new BatchResponse(
                batch.getBatchName(),
                batch.getIntakeYear(),
                batch.getIntakeMonth().name(),
                batch.getStudentCount(),
                batch.getStatus().name()
        );
    }
}
