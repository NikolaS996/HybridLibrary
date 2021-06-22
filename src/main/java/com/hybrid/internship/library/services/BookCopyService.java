package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.BookCopy;

import java.util.List;

public interface BookCopyService extends AbstractService<BookCopy, Long> {

    List<BookCopy> findAllByBookId(Long id);

    void deleteAllByBookId(Long id);

}
