package org.example.ictdepartmentmanagementsystem.service;

import org.example.ictdepartmentmanagementsystem.dto.AuthResponse;
import org.example.ictdepartmentmanagementsystem.dto.LoginRequest;
import org.example.ictdepartmentmanagementsystem.dto.AdminRegisterRequest;
import org.example.ictdepartmentmanagementsystem.entity.Role;
import org.example.ictdepartmentmanagementsystem.entity.User;
import org.example.ictdepartmentmanagementsystem.repository.UserRepository;
import org.example.ictdepartmentmanagementsystem.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register (AdminRegisterRequest adminRegisterRequest) {
        if(userRepository.existsByEnrollmentNumber(adminRegisterRequest.getEnrollmentNumber())) {
            throw new IllegalArgumentException("Enrollment number already exists");
        }

        if (userRepository.existsByEmail(adminRegisterRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setEnrollmentNumber(adminRegisterRequest.getEnrollmentNumber());
        user.setFullName(adminRegisterRequest.getFullName());
        user.setNameWithInitials(adminRegisterRequest.getNameWithInitials());
        user.setEmail(adminRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(adminRegisterRequest.getPassword()));
        user.setRole(Role.valueOf(adminRegisterRequest.getRole()));

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEnrollmentNumber());

        return new AuthResponse(token,user.getEnrollmentNumber(),user.getRole().name());
    }

    public AuthResponse login (LoginRequest loginRequest) {


            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEnrollmentNumber(), loginRequest.getPassword())
            );


        String token = jwtUtil.generateToken(loginRequest.getEnrollmentNumber());

        User user = userRepository.findByEnrollmentNumber(loginRequest.getEnrollmentNumber());

        if (user == null) {
            throw new IllegalArgumentException("Enrollment number does not exist");
        }

        return new AuthResponse(token,user.getEnrollmentNumber(),user.getRole().name());
    }
}
