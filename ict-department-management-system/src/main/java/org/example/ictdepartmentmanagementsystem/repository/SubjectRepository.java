package org.example.ictdepartmentmanagementsystem.repository;

import org.example.ictdepartmentmanagementsystem.entity.Semester;
import org.example.ictdepartmentmanagementsystem.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    List<Subject> findBySemester(Semester semester);
    boolean existsBySubjectCode(String subjectCode);
}
