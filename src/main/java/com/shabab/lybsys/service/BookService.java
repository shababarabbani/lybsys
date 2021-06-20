package com.shabab.lybsys.service;

import com.shabab.lybsys.entity.Book;
import com.shabab.lybsys.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getByID(Long id) {
        Optional<Book> book=bookRepository.findById(id);
        return book.orElse(null);
    }
}
