package com.hybrid.internship.library.services;

import com.hybrid.internship.library.dtos.BookDto;

import java.util.List;

public interface BookService extends AbstractService<BookDto, Long>{

    List<BookDto> findAll();

    BookDto findById(Long id);

    List<BookDto> findAllByName(String name);

    List<BookDto> findAllByAuthor(String author);

    BookDto create(BookDto book);

    BookDto update(BookDto book);

    //BookDto mostRentedBook();

    void delete(Long id);

    boolean exists(Long id);
}
