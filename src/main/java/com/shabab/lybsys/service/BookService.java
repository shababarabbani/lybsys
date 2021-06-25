package com.shabab.lybsys.service;

import com.shabab.lybsys.entity.Book;
import com.shabab.lybsys.exception.BadRequestException;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooks() throws ResourceNotFoundException {
        List<Book> books=bookRepository.findAll();

        if(books.isEmpty()){
            throw new ResourceNotFoundException("No books found!");
        }

        return bookRepository.findAll();
    }

    public Book getByID(Long id) throws ResourceNotFoundException {
        Optional<Book> book=bookRepository.findById(id);
        return book.orElseThrow(()-> new ResourceNotFoundException("No book found for id: "+id));
    }

    public List<Book> searchBooks(String searchKey) throws BadRequestException, ResourceNotFoundException {
        if(searchKey==null || searchKey.equalsIgnoreCase("")) throw new BadRequestException("Search key cannot be null");

        List<Book> books=bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(searchKey,searchKey);

        if(books.isEmpty()) throw new ResourceNotFoundException("No books found for search key: "+searchKey);

        return books;
    }

    public Book createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public Book updateBook(Book book, Long bookId) throws ResourceNotFoundException {
        Book existingBook = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException(String.format("Book with Id-%s not found!", bookId)));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublishDate(book.getPublishDate());
        existingBook.setGenre(book.getGenre());
        existingBook.setBlurb(book.getBlurb());

        Book updatedBook = bookRepository.save(existingBook);
        return updatedBook;
    }

    public void deleteBook(Long bookId) throws ResourceNotFoundException {
        Book bookToBeDeleted = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException(String.format("Book with Id-%s not found!", bookId)));
        bookRepository.delete(bookToBeDeleted);
    }
}
