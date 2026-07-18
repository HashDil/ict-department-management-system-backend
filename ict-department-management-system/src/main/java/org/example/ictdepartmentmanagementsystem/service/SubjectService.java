package org.example.ictdepartmentmanagementsystem.service;

import org.example.ictdepartmentmanagementsystem.dto.AddSubjectRequest;
import org.example.ictdepartmentmanagementsystem.dto.SubjectResponse;
import org.example.ictdepartmentmanagementsystem.entity.Semester;
import org.example.ictdepartmentmanagementsystem.entity.Subject;
import org.example.ictdepartmentmanagementsystem.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectResponse addSubject(AddSubjectRequest request) {
        if(subjectRepository.existsBySubjectCode(request.getSubjectCode())) {
            throw new IllegalArgumentException("Subject code already exists");
        }
        Subject subject = new Subject();
        subject.setSubjectCode(request.getSubjectCode());
        subject.setSubjectName(request.getSubjectName());
        subject.setCreditHours(request.getCreditHours());
        subject.setSemester(request.getSemester());

        return mapToResponse(subjectRepository.save(subject));
    }

    public List<SubjectResponse>getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<SubjectResponse> getSubjectsBySemester(Semester semester) {
        return subjectRepository.findBySemester(semester)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SubjectResponse mapToResponse(Subject subject) {
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setSubjectCode(subject.getSubjectCode());
        subjectResponse.setSubjectName(subject.getSubjectName());
        subjectResponse.setCreditHours(subject.getCreditHours());
        subjectResponse.setSemester(subject.getSemester().name());

        return subjectResponse;
    }
}
