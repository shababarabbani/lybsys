package com.shabab.lybsys.controller;

import com.shabab.lybsys.entity.Book;
import com.shabab.lybsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/books")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return books;
    }

}
