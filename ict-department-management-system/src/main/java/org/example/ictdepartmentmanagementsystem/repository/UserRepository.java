package org.example.ictdepartmentmanagementsystem.repository;

import org.example.ictdepartmentmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEnrollmentNumber(String enrollmentNumber);

    boolean existsByEnrollmentNumber(String enrollmentNumber);

    boolean existsByEmail(String email);

    void deleteByEnrollmentNumber(String enrollmentNumber);
}
