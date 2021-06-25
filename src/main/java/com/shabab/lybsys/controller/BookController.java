package com.shabab.lybsys.controller;

import com.shabab.lybsys.entity.Book;
import com.shabab.lybsys.exception.BadRequestException;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/books")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() throws ResourceNotFoundException {
        List<Book> books = bookService.getAllBooks();
        return books;
    }

    @GetMapping("/{id}")
    public Book getByID(@PathVariable Long id) throws ResourceNotFoundException {
        Book book = bookService.getByID(id);
        return book;
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String searchKey) throws BadRequestException, ResourceNotFoundException {
        List<Book> books = bookService.searchBooks(searchKey);
        return books;
     }


    @PostMapping("/create")
    public Book createBook(@RequestBody Book book){

        Book createdBook = bookService.createBook(book);
        return createdBook;
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book, @RequestParam Long bookId) throws ResourceNotFoundException {

        Book updatedBook = bookService.updateBook(book, bookId);
        return updatedBook;
    }

    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam Long bookId) throws ResourceNotFoundException {
        bookService.deleteBook(bookId);
    }
}
