package com.shabab.lybsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "student")
public class Student extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    private String rollNumber;
    private String name;
    private String contactNo;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false, referencedColumnName = "dept_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<IssuedBook> issuedBooks;
}