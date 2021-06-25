package com.shabab.lybsys.controller;

import com.shabab.lybsys.entity.Department;
import com.shabab.lybsys.entity.Student;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.service.DepartmentService;
import com.shabab.lybsys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/departments")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllDepartments() throws ResourceNotFoundException {
        List<Department> departments = departmentService.getAllDepartments();
        return departments;
    }
}

