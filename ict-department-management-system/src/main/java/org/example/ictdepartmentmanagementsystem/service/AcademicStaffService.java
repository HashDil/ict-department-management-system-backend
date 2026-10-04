package org.example.ictdepartmentmanagementsystem.service;

import org.example.ictdepartmentmanagementsystem.dto.AcademicStaffResponse;
import org.example.ictdepartmentmanagementsystem.dto.AddAcademicStaffRequest;
import org.example.ictdepartmentmanagementsystem.dto.UpdateAcademicStaffRequest;
import org.example.ictdepartmentmanagementsystem.entity.AcademicStaff;
import org.example.ictdepartmentmanagementsystem.entity.Honorific;
import org.example.ictdepartmentmanagementsystem.repository.AcademicStaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcademicStaffService {

    private final AcademicStaffRepository academicStaffRepository;
    private final FileStorageService fileStorageService;

    public AcademicStaffService(AcademicStaffRepository academicStaffRepository, FileStorageService fileStorageService) {
        this.academicStaffRepository = academicStaffRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<AcademicStaffResponse>getAllAcademicStaff(){
        return academicStaffRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public String addAcademicStaff (AddAcademicStaffRequest request){
        AcademicStaff academicStaff = new AcademicStaff();
        academicStaff.setName(request.getName());
        academicStaff.setEmail(request.getEmail());
        academicStaff.setHonorific(Honorific.valueOf(request.getHonorific().toUpperCase()));
        academicStaff.setPositions(request.getPositions());
        academicStaff.setTitle(request.getTitle());
        academicStaff.setQualifications(request.getQualifications());
        academicStaff.setResearchInterests(request.getResearchInterests());
        academicStaff.setPhoneNumber(request.getPhoneNumber());

        System.out.println("DTO Honorific = " + request.getHonorific());
        System.out.println("Entity Honorific = " + academicStaff.getHonorific());

        academicStaffRepository.save(academicStaff);

        return request.getHonorific()+". "+request.getName()+" saved successfully, ";
    }

    public String updateAcademicStaff (String email, UpdateAcademicStaffRequest request){
        AcademicStaff academicStaff = academicStaffRepository.findAcademicStaffByEmail(email);

        academicStaff.setName(request.getName());
        academicStaff.setHonorific(Honorific.valueOf(request.getHonorific().toUpperCase()));
        academicStaff.setPositions(request.getPositions());
        academicStaff.setTitle(request.getTitle());
        academicStaff.setQualifications(request.getQualifications());
        academicStaff.setResearchInterests(request.getResearchInterests());
        academicStaff.setPhoneNumber(request.getPhoneNumber());
        academicStaffRepository.save(academicStaff);

        return request.getHonorific()+". "+request.getName()+" updated successfully";
    }



    public String deleteAcademicStaff (String email) throws IOException {
        AcademicStaff academicStaff = academicStaffRepository.findAcademicStaffByEmail(email);

        if (academicStaff != null) {
            fileStorageService.deleteAcademicStaffPicture(academicStaff.getPicture());
            academicStaffRepository.delete(academicStaff);
            return email+" deleted successfully";
        }

        return email+" not found";

    }

    public String addPictureToAcademicStaff (MultipartFile file, String email) throws IOException {
        AcademicStaff staff = academicStaffRepository.findAcademicStaffByEmail(email);

        if (staff == null) {
            throw new IllegalArgumentException("No academicStaff found for this email");
        }

        if(staff.getPicture()!=null){
            fileStorageService.deleteAcademicStaffPicture(staff.getPicture());
        }

       String relativePath = fileStorageService.saveAcademicStaffPicture(file, email);
        staff.setPicture(relativePath);
        academicStaffRepository.save(staff);

        return "Picture saved successfully";

    }

    public String deletePicture (String email) throws IOException {
        AcademicStaff staff = academicStaffRepository.findAcademicStaffByEmail(email);
        if (staff != null && staff.getPicture()!=null) {
            fileStorageService.deleteAcademicStaffPicture(staff.getPicture());
            staff.setPicture(null);
            academicStaffRepository.save(staff);
        }

        return "Picture deleted successfully";
    }


    private AcademicStaffResponse mapToResponse(AcademicStaff academicStaff){
        AcademicStaffResponse academicStaffResponse = new AcademicStaffResponse();

        academicStaffResponse.setDisplayName(academicStaff.getHonorific()+". "+academicStaff.getName());
        academicStaffResponse.setEmail(academicStaff.getEmail());
        academicStaffResponse.setPhoneNumber(academicStaff.getPhoneNumber());
        academicStaffResponse.setTitle(academicStaff.getTitle().name());
        academicStaffResponse.setPositions(academicStaff.getPositions());
        academicStaffResponse.setQualifications(academicStaff.getQualifications());
        academicStaffResponse.setResearchInterests(academicStaff.getResearchInterests());
        academicStaffResponse.setPicture(academicStaff.getPicture());


        return academicStaffResponse;
    }

}
