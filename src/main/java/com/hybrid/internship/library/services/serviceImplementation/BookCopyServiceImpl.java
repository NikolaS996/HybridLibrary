package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.repositories.BookCopyRepository;
import com.hybrid.internship.library.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Override
    public List<BookCopy> findAll() {
        return bookCopyRepository.findAll();
    }

    @Override
    public List<BookCopy> findAllByBookId(Long id) {
        return bookCopyRepository.findAllByBookId(id);
    }

    @Override
    public BookCopy findById(Long id) {
        return bookCopyRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public BookCopy create(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    @Override
    public BookCopy update(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    @Override
    public void delete(Long id) {
        bookCopyRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return bookCopyRepository.existsById(id);
    }
}
