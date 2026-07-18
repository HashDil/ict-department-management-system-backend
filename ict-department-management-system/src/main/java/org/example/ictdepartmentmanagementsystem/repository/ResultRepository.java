package org.example.ictdepartmentmanagementsystem.repository;

import org.example.ictdepartmentmanagementsystem.entity.Result;
import org.example.ictdepartmentmanagementsystem.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    List<Result> findByStudentId(Long studentId);
    List<Result> findByStudentIdAndSemester(Long studentId, Semester semester);
}
