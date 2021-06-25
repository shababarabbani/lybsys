package com.shabab.lybsys.service;

import com.shabab.lybsys.entity.Department;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.repository.DeparmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DeparmentRepository deparmentRepository;

    public List<Department> getAllDepartments() throws ResourceNotFoundException {
        List<Department> departments= deparmentRepository.findAll();
        if(departments.isEmpty()) throw new ResourceNotFoundException("No departments found");
        return departments;

    }
}
