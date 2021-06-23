package com.shabab.lybsys.repository;

import com.shabab.lybsys.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    public List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String titleKey, String authorKey);

}
