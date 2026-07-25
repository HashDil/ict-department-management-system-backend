package org.example.ictdepartmentmanagementsystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.base.url}")
    private String baseUrl;

    private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/jpg", "image/png");

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    public String saveProfilePicture(MultipartFile file, String enrollmentNumber) throws IOException {

        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("Only JPG and PNG images are allowed");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File is must be less than 5MB");
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String extension = getFileExtension(file.getOriginalFilename());
        String fileName = enrollmentNumber.replace("/","-")+"."+extension;

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/profile-pictures/" + fileName;
    }

    public void deleteProfilePicture(String relativePath) throws IOException {
        if(relativePath == null || relativePath.isEmpty()) {return;}

        Path filePath = Paths.get(uploadDir)
                .resolve(Paths.get(relativePath).getFileName());

        Files.deleteIfExists(filePath);
    }

    public String getFileUrl(String relativePath) throws IOException {
        if(relativePath == null || relativePath.isEmpty()) {return null;}
        return baseUrl+relativePath;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.contains(".")) {
            return "jpg";
        }
        return fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
    }
}
