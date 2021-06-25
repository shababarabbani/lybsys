package com.shabab.lybsys.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "student")
public class Student extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String rollNumber;
    private String name;
    private String contactNo;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false, referencedColumnName = "dept_id")
    private Department department;


}
