package org.example.ictdepartmentmanagementsystem.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.upload.academicstaff.dir}")
    private String academicStaffUploadDir;


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registerHandler(registry, uploadDir, "/uploads/profile-pictures/**");
        registerHandler(registry, academicStaffUploadDir, "/uploads/academicstaff-pictures/**");
    }

    private void registerHandler(ResourceHandlerRegistry registry, String dir, String pattern) {
        Path path = Paths.get(dir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory: " + path, e);
        }

        String location = path.toUri().toString();
        log.info("Serving {} from resolved location: {}", pattern, location);

        registry.addResourceHandler(pattern)
                .addResourceLocations(location);
    }
}