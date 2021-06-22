package com.hybrid.internship.library.services.impl;

import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.repositories.BookCopyRepository;
import com.hybrid.internship.library.services.BookCopyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookCopyServiceImpl implements BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Override
    public List<BookCopy> findAll() {
        return bookCopyRepository.findAll();
    }

    @Override
    public List<BookCopy> findAllByBookId(Long id) {
        return bookCopyRepository.findAllByBookId(id);
    }

    @Override
    public Optional<BookCopy> findById(Long id) {
        return bookCopyRepository.findById(id);
    }

    @Override
    public BookCopy create(BookCopy bookCopy) {
        BookCopy createdBookCopy = bookCopyRepository.save(bookCopy);
        log.info("Book copy {} is created.", createdBookCopy);
        return createdBookCopy;
    }

    @Override
    public BookCopy update(BookCopy bookCopy) {
        BookCopy updatedBook = bookCopyRepository.save(bookCopy);
        log.info("Book copy {} is created.", updatedBook);
        return updatedBook;
    }

    @Override
    public void delete(Long id) {
        bookCopyRepository.deleteById(id);
        log.info("Book copy with ID {} is successfully deleted.", id);
    }

    @Override
    public void deleteAllByBookId(Long id) {
        bookCopyRepository.deleteAllByBookId(id);
        log.info("All copies of book with ID {} are successfully deleted.", id);
    }

    @Override
    public boolean exists(Long id) {
        return bookCopyRepository.existsById(id);
    }
}
