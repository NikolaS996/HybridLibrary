package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.repositories.BookRepository;
import com.hybrid.internship.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseGet(() -> null);
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
        bookRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return bookRepository.existsById(id);
    }
}
