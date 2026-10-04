package org.example.ictdepartmentmanagementsystem.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String enrollmentNumber;
    private String fullName;
    private String nameWithInitials;
    private String email;
    private String role;
    private String batchName;
    private String profilePicture;
}
