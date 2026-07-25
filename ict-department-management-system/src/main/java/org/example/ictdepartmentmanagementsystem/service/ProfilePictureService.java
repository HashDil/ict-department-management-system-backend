package org.example.ictdepartmentmanagementsystem.service;

import org.example.ictdepartmentmanagementsystem.entity.User;
import org.example.ictdepartmentmanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ProfilePictureService {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public ProfilePictureService(UserRepository userRepository, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    public Map<String, String> uploadProfilePicture(String enrollmentNumber,MultipartFile file) throws IOException {
        User user =  userRepository.findByEnrollmentNumber(enrollmentNumber);

        if(user==null){
            throw new RuntimeException("Student not found");
        }

        if(user.getProfilePicture()!=null){
            fileStorageService.deleteProfilePicture(user.getProfilePicture());
        }

        String relativePath = fileStorageService.saveProfilePicture(file,enrollmentNumber);

        user.setProfilePicture(relativePath);
        userRepository.save(user);

        return Map.of(
                "message","Profile picture uploaded successfully",
                "url", fileStorageService.getFileUrl(relativePath)
        );
    }

    public void deleteProfilePicture(String enrollmentNumber) throws IOException {
        User user =  userRepository.findByEnrollmentNumber(enrollmentNumber);

        if(user==null){
            throw new RuntimeException("Student not found");
        }
        if(user.getProfilePicture()==null){
            throw new RuntimeException("No profile picture found");
        }

        fileStorageService.deleteProfilePicture(user.getProfilePicture());
        user.setProfilePicture(null);
        userRepository.save(user);
    }
}
