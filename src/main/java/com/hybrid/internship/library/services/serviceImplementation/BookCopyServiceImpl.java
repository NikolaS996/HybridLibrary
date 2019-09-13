package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.services.BookCopyService;

import java.util.List;

public class BookCopyServiceImpl implements BookCopyService {
    @Override
    public List<BookCopy> getAll() {
        return null;
    }

    @Override
    public List<BookCopy> findAllByBookId(Long id) {
        return null;
    }

    @Override
    public BookCopy getOne(Long id) {
        return null;
    }

    @Override
    public BookCopy create(BookCopy bookCopy) {
        return null;
    }

    @Override
    public BookCopy update(BookCopy bookCopy) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean exists(Long id) {
        return false;
    }
}
