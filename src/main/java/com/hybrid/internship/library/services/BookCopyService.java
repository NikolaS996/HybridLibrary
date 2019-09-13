package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.BookCopy;

import java.util.List;

public interface BookCopyService extends AbstractService<BookCopy, Long> {

    List<BookCopy> getAll();

    List<BookCopy> findAllByBookId(Long id);

    BookCopy getOne(Long id);

    BookCopy create(BookCopy bookCopy);

    BookCopy update(BookCopy bookCopy);

    void delete(Long id);

    boolean exists(Long id);

}
