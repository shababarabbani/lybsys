package com.shabab.lybsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "book")
public class Book extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    private String title;
    private String author;
    private Date publishDate;
    private String genre;
    private String blurb;
    private Integer count;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private Set<IssuedBook> issuedBooks;
}
