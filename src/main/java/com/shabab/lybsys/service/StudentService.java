package com.shabab.lybsys.service;

import com.shabab.lybsys.entity.Book;
import com.shabab.lybsys.entity.Department;
import com.shabab.lybsys.entity.Student;
import com.shabab.lybsys.exception.BadRequestException;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.repository.DeparmentRepository;
import com.shabab.lybsys.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DeparmentRepository deparmentRepository;


    public List<Student> getAllStudents() throws ResourceNotFoundException {
        List<Student> students = studentRepository.findAll();

        if(students.isEmpty()){
            throw new ResourceNotFoundException("No student found!");
        }
        return students;
    }

    public Student getByID(Long id) throws ResourceNotFoundException{
        Optional<Student> student = studentRepository.findById(id);
        return student.orElseThrow(()-> new ResourceNotFoundException("No student found with id " + id));
    }

    public List<Student> searchStudents(String searchKey) throws BadRequestException, ResourceNotFoundException {
        if (searchKey == null || searchKey.equalsIgnoreCase("")) throw new BadRequestException("Search Key cannot be null");

        List<Student> students = studentRepository.findByNameContainingIgnoreCaseOrRollNumberContainingOrContactNoContainingOrEmailContainingIgnoreCase(searchKey, searchKey,searchKey,searchKey);

        if (students.isEmpty()) throw new ResourceNotFoundException("No students found for search key " + searchKey);

        return students;
    }

    public Student createStudent(Student student, Long deptId) throws ResourceNotFoundException {
        Department dept = deparmentRepository.findById(deptId).orElseThrow(()-> new ResourceNotFoundException(String.format("Department with id - %s not found", deptId)));
        student.setDepartment(dept);
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    public Student updateStudent(Student student, Long studentId, Long deptId) throws ResourceNotFoundException{
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException(String.format("Student with id - %s not found", studentId)));
        Department dept = deparmentRepository.findById(deptId).orElseThrow(()-> new ResourceNotFoundException(String.format("Department with id - %s not found", deptId)));
        existingStudent.setName(student.getName());
        existingStudent.setRollNumber(student.getRollNumber());
        existingStudent.setContactNo(student.getContactNo());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(dept);

        Student updatedStudent = studentRepository.save(existingStudent);
        return updatedStudent;
    }

    public void deleteStudent(Long studentId) throws ResourceNotFoundException{
        Student studentToBeDeleted = studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException(String.format("Student with id - %s not found", studentId)));
        studentRepository.delete(studentToBeDeleted);
    }
}
