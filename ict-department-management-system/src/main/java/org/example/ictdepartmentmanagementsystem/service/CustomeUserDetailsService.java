package org.example.ictdepartmentmanagementsystem.service;


import org.example.ictdepartmentmanagementsystem.entity.User;
import org.example.ictdepartmentmanagementsystem.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String enrollmentNumber) throws UsernameNotFoundException {
        User user = userRepository.findByEnrollmentNumber(enrollmentNumber);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: "+enrollmentNumber);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEnrollmentNumber())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().name())))
                .build();
    }
}
