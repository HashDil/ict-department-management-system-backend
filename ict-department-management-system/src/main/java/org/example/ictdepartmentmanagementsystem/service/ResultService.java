package org.example.ictdepartmentmanagementsystem.service;

import org.example.ictdepartmentmanagementsystem.dto.AddResultRequest;
import org.example.ictdepartmentmanagementsystem.dto.ResultResponse;
import org.example.ictdepartmentmanagementsystem.dto.SubjectResponse;
import org.example.ictdepartmentmanagementsystem.dto.UserResponse;
import org.example.ictdepartmentmanagementsystem.entity.Result;
import org.example.ictdepartmentmanagementsystem.entity.Semester;
import org.example.ictdepartmentmanagementsystem.entity.Student;
import org.example.ictdepartmentmanagementsystem.entity.Subject;
import org.example.ictdepartmentmanagementsystem.repository.ResultRepository;
import org.example.ictdepartmentmanagementsystem.repository.SubjectRepository;
import org.example.ictdepartmentmanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectService subjectService;

    public ResultService(ResultRepository resultRepository, UserRepository userRepository, SubjectRepository subjectRepository, SubjectService subjectService) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;

    }

    public ResultResponse addResult(AddResultRequest request){
        Student student = (Student) userRepository.findByEnrollmentNumber(request.getEnrollmentNumber());

        if (student == null){
            throw new IllegalArgumentException("Student not found");
        }

        Subject subject = subjectRepository.findBySubjectCode(request.getSubjectCode());

        if (subject == null){
            throw new IllegalArgumentException("Subject not found");
        }

        Result result = new Result();
        result.setSubject(subject);
        result.setStudent(student);
        result.setMarks(request.getMarks());
        result.setGrade(request.getGrade());
        result.setSemester(request.getSemester());

        return mapToResponse(resultRepository.save(result));
    }

    public List<ResultResponse> getStudentResults(String enrollmentNumber){
        Student student = (Student) userRepository.findByEnrollmentNumber(enrollmentNumber);
        if (student == null){
            throw new IllegalArgumentException("Student not found");
        }

        return resultRepository.findByStudentEnrollmentNumber(student.getEnrollmentNumber())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ResultResponse> getStudentResultsBySemester(String enrollmentNumber, Semester semester){
        Student student = (Student) userRepository.findByEnrollmentNumber(enrollmentNumber);
        if (student == null){
            throw new IllegalArgumentException("Student not found");
        }

        return resultRepository.findByStudentEnrollmentNumberAndSemester(enrollmentNumber,semester)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ResultResponse mapToResponse(Result result){
        Student student = result.getStudent();

        UserResponse response = new UserResponse();
        response.setEnrollmentNumber(student.getEnrollmentNumber());
        response.setEmail(student.getEmail());
        response.setFullName(student.getFullName());
        response.setNameWithInitials(student.getNameWithInitials());
        response.setRole(student.getRole().toString());
        response.setBatch(student.getBatch().toString());


        SubjectResponse subResponse = subjectService.mapToResponse(result.getSubject());

        return new  ResultResponse(
                response,
                subResponse,
                result.getMarks(),
                result.getGrade(),
                result.getGrade().getGradePoint(),
                result.getSemester()
        );
    }
}
