package org.example.ictdepartmentmanagementsystem.repository;

import org.example.ictdepartmentmanagementsystem.entity.Role;
import org.example.ictdepartmentmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEnrollmentNumber(String enrollmentNumber);

    boolean existsByEnrollmentNumber(String enrollmentNumber);

    boolean existsByEmail(String email);

    void deleteByEnrollmentNumber(String enrollmentNumber);

    List<User> findByRole(Role role);
}
