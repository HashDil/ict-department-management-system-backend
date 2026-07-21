package org.example.ictdepartmentmanagementsystem.service;

import org.example.ictdepartmentmanagementsystem.dto.ChangePasswordRequest;
import org.example.ictdepartmentmanagementsystem.dto.UpdateProfileRequest;
import org.example.ictdepartmentmanagementsystem.dto.UserResponse;
import org.example.ictdepartmentmanagementsystem.entity.Student;
import org.example.ictdepartmentmanagementsystem.entity.User;
import org.example.ictdepartmentmanagementsystem.repository.BatchRepository;
import org.example.ictdepartmentmanagementsystem.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponse mapToResponse(User user) {
        String batchName = null;

        if (user instanceof Student student) {
            batchName = student.getBatch().getBatchName();
        }
        return new UserResponse(
                user.getEnrollmentNumber(),
                user.getFullName(),
                user.getNameWithInitials(),
                user.getEmail(),
                user.getRole().name(),
                batchName
        );
    }

    public UserResponse getUserByEnrollmentNumber(String enrollmentNumber) {
        User user = userRepository.findByEnrollmentNumber(enrollmentNumber);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return mapToResponse(user);
    }

    public void changePassword(String enrollmentNumber, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findByEnrollmentNumber(enrollmentNumber);

        if(!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(),user.getPassword())){
            throw new BadCredentialsException("Current password is incorrect");
        }

        if(changePasswordRequest.getCurrentPassword().equals(changePasswordRequest.getNewPassword())){
            throw new IllegalArgumentException("New password must be different from the current password");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    public UserResponse updateProfile(UpdateProfileRequest request){
        User user = userRepository.findByEnrollmentNumber(request.getEnrollmentNumber());

        if(!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email address already in use");
        }
        user.setFullName(request.getFullName());
        user.setNameWithInitials(request.getNameWithInitials());
        user.setEmail(request.getEmail());

        return mapToResponse(userRepository.save(user));
    }


}
