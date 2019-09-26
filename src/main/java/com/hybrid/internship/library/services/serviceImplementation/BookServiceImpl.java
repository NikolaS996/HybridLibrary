package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.exceptions.RentedCopiesException;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.repositories.BookRepository;
import com.hybrid.internship.library.services.BookCopyService;
import com.hybrid.internship.library.services.BookRentalService;
import com.hybrid.internship.library.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    BookRentalService bookRentalService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllByName(String name) {
        return bookRepository.findAllByName(name);
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        if(bookRentalService.rentedCopies(id) < 1){
            bookCopyService.deleteAllByBookId(id);
            bookRepository.deleteById(id);
        }
        else throw new RentedCopiesException();
    }

    @Override
    public boolean exists(Long id) {
        return bookRepository.existsById(id);
    }
}
