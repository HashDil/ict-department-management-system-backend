package org.example.ictdepartmentmanagementsystem.repository;

import org.example.ictdepartmentmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEnrollmentNumber(String enrollmentNumber);
}
