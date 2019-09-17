package com.hybrid.internship.library.services;

import com.hybrid.internship.library.dtos.BookCopyDto;

import java.util.List;

public interface BookCopyService extends AbstractService<BookCopyDto, Long> {

    List<BookCopyDto> findAll();

    List<BookCopyDto> findAllByBookId(Long id);

    BookCopyDto findById(Long id);

    BookCopyDto create(BookCopyDto bookCopy);

    BookCopyDto update(BookCopyDto bookCopy);

    void delete(Long id);

    void deleteAllByBookId(Long id);

    boolean exists(Long id);

}
