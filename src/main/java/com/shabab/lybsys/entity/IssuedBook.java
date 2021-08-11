package com.shabab.lybsys.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "issued_book")
public class IssuedBook extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issuedBookId;

    private LocalDate issuedOn;
    private LocalDate issuedTill;
    private LocalDate returnedOn;

    private Boolean isCurrentlyIssued;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, referencedColumnName = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "student_id")
    private Student student;

}
