package com.shabab.lybsys.service;

import com.shabab.lybsys.entity.Book;
import com.shabab.lybsys.entity.IssuedBook;
import com.shabab.lybsys.entity.Student;
import com.shabab.lybsys.exception.BadRequestException;
import com.shabab.lybsys.exception.ResourceNotFoundException;
import com.shabab.lybsys.repository.BookRepository;
import com.shabab.lybsys.repository.IssuedBookRepository;
import com.shabab.lybsys.repository.StudentRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    @Value("${books.issue-period}")
    private String bookIssuePeriod;


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

    public List<IssuedBook> issueBooks(Long studentId, List<Long> bookIds) throws ResourceNotFoundException {
        Student student= studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException(String.format("Student with Id-%s not found!", studentId)));
        List<Book> booksToBeIssued=bookRepository.findAllByBookIdIn(bookIds);

        List<IssuedBook> issuedBooks = booksToBeIssued.stream().filter(book -> book.getCount() > 0).map((book)-> {
                    IssuedBook alreadyIssuedBook=issuedBookRepository.findByBookIdAndStudentIdAndIsCurrentlyIssued(book.getBookId(), student.getStudentId(), true);
                    IssuedBook issuedBook=null;
                    if(alreadyIssuedBook==null) {
                        book.setCount(book.getCount() - 1);
                        issuedBook = new IssuedBook();
                        issuedBook.setBook(book);
                        issuedBook.setStudent(student);
                        issuedBook.setIssuedOn(LocalDate.now());
                        issuedBook.setIssuedTill(LocalDate.now().plusDays(Integer.parseInt(bookIssuePeriod)));
                        issuedBook.setIsCurrentlyIssued(true);
                        issuedBookRepository.save(issuedBook);
                    }
                    return issuedBook;
                }).collect(Collectors.toList());

        return issuedBooks;
    }

    public List<IssuedBook> returnBooks(List<Long> issuedBookIds) throws ResourceNotFoundException {
        List<IssuedBook> issuedBooks=issuedBookRepository.findAllByIssuedBookIdIn(issuedBookIds);

        List<IssuedBook> returnedBooks=issuedBooks.stream().map(issuedBook -> {
            issuedBook.getBook().setCount(issuedBook.getBook().getCount() + 1);
            issuedBook.setIsCurrentlyIssued(false);
            issuedBook.setReturnedOn(LocalDate.now());
            issuedBookRepository.save(issuedBook);
            return issuedBook;
        }).collect(Collectors.toList());

        return returnedBooks;

    }

    public List<IssuedBook> getIssuedBooksByStudentId(Long studentId, Boolean includeHistory) throws ResourceNotFoundException {
        Student student= studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException(String.format("Student with Id-%s not found!", studentId)));

        Set<IssuedBook> issuedBooks=student.getIssuedBooks();
        if(includeHistory){
            return new ArrayList<>(issuedBooks);
        }else{
            return issuedBooks.stream().filter(IssuedBook::getIsCurrentlyIssued).collect(Collectors.toList());
        }
    }
}