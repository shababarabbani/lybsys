package com.shabab.lybsys.controller;

import com.shabab.lybsys.entity.Student;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

