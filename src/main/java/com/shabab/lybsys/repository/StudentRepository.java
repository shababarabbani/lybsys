package com.shabab.lybsys.repository;

import com.shabab.lybsys.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCaseOrRollNumberContainingOrContactNoContainingOrEmailContainingIgnoreCase(String nameKey, String rollNoKey, String contactNoKey, String emailKey);
}
