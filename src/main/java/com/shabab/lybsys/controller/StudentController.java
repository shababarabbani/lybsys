package com.shabab.lybsys.controller;

import com.shabab.lybsys.entity.Book;
import com.shabab.lybsys.entity.Student;
import com.shabab.lybsys.exception.BadRequestException;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() throws ResourceNotFoundException {
        List<Student> students = studentService.getAllStudents();
        return students;
    }

    @GetMapping("/{id}")
    public Student getByID(@PathVariable Long id) throws ResourceNotFoundException{
        Student student = studentService.getByID(id);
        return student;
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String searchKey) throws BadRequestException, ResourceNotFoundException{
        List<Student> students = studentService.searchStudents(searchKey);
        return students;
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student, @RequestParam Long deptId) throws ResourceNotFoundException {
        Student createdStudent = studentService.createStudent(student, deptId);
        return createdStudent;
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student, @RequestParam Long studentId, @RequestParam Long deptId) throws ResourceNotFoundException{
        Student updatedStudent = studentService.updateStudent(student, studentId, deptId);
        return updatedStudent;
    }

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestParam Long studentId) throws ResourceNotFoundException{
        studentService.deleteStudent(studentId);
    }
}

