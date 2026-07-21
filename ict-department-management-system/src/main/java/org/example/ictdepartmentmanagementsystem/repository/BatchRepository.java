package org.example.ictdepartmentmanagementsystem.repository;

import org.example.ictdepartmentmanagementsystem.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch,Long> {
    boolean existsBatchByBatchName(String batchName);
    Batch findByBatchName(String batchName);

    void deleteByBatchName(String batchName);
}
