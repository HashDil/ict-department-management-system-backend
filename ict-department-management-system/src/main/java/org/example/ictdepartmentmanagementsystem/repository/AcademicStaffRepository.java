package org.example.ictdepartmentmanagementsystem.repository;

import org.example.ictdepartmentmanagementsystem.entity.AcademicStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicStaffRepository extends JpaRepository<AcademicStaff,Long> {
    AcademicStaff findAcademicStaffByEmail(String email);
}
