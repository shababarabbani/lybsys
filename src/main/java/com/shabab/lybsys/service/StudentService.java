package com.shabab.lybsys.service;

import com.shabab.lybsys.entity.Student;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> getAllStudents() throws ResourceNotFoundException {
        List<Student> students = studentRepository.findAll();

        if(students.isEmpty()){
            throw new ResourceNotFoundException("No student found!");
        }
        return students;
    }
}
