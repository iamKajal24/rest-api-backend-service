package in.kajalCoder.crudDtoDemo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.kajalCoder.crudDtoDemo.dto.CreateStudentRequestDTO;
import in.kajalCoder.crudDtoDemo.dto.CreateStudentResponseDto;
import in.kajalCoder.crudDtoDemo.dto.UpdateStudentRequestDto;
import in.kajalCoder.crudDtoDemo.dto.UpdateStudentResponseDto;
import in.kajalCoder.crudDtoDemo.entity.Student;
import in.kajalCoder.crudDtoDemo.exception.DuplicateResourceException;
import in.kajalCoder.crudDtoDemo.exception.ResourceNotFoundException;
import in.kajalCoder.crudDtoDemo.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDTO studentRequestDTO) {
        // business logic
        // store to db
        Student student = mapToEntity(studentRequestDTO);

        if (emailExits(student)) {
            throw new DuplicateResourceException("Student with email " + student.getEmail() + " alreadyExits");
        }

        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);

    }

    public CreateStudentResponseDto getStudent(Long id) {
        Student studentResp = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student with id " + id + " not found"));

        return mapToDto(studentResp);
    }

    public List<CreateStudentResponseDto> getAllStudent() {
        // select * from student where deleted =false
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        System.out.println("Total students fetched = " + studentList.size());
        return studentList.stream()
                .map(this::mapToDto)
                .toList();
    }

    public UpdateStudentResponseDto updateStudent(Long id,
            UpdateStudentRequestDto studentReq) {

        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("student with id " + id + " not found"));

        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);

        return mapToUpdateDto(savedStudent);
    }

    public void deleteStudent(Long id) {
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student with id " + id + " not found"));

        studentRepository.delete(studentToBeDeleted);
        ;

    }

    public void deleteStudentSoftly(Long id) {
        // get
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student with id " + id + " not found"));

        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted);

    }

    private Student mapToEntity(CreateStudentRequestDTO studentRequestDTO) {

        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setAge(studentRequestDTO.getAge());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setSubject(studentRequestDTO.getSubject());
        student.setDeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return student;

    }

    private CreateStudentResponseDto mapToDto(Student student) {

        CreateStudentResponseDto responseDto = new CreateStudentResponseDto();
        responseDto.setName(student.getName());
        responseDto.setEmail(student.getEmail());
        responseDto.setAge(student.getAge());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setId(student.getId());
        responseDto.setMessage("Student saved successfully");
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());
        return responseDto;
    }

    private UpdateStudentResponseDto mapToUpdateDto(Student student) {

        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();
        responseDto.setName(student.getName());
        responseDto.setEmail(student.getEmail());
        responseDto.setAge(student.getAge());
        responseDto.setRollNo(student.getRollNo());
        responseDto.setSubject(student.getSubject());
        responseDto.setId(student.getId());
        responseDto.setMessage("Student updated successfully");
        responseDto.setUpdatedAt(student.getUpdatedAt());
        return responseDto;

    }

    private boolean emailExits(Student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }
}
