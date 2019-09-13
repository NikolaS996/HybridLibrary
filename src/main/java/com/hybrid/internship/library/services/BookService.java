package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.Book;

import java.util.List;

public interface BookService extends AbstractService<Book, Long>{

    List<Book> getAll();

    Book getOne(Long id);

    Book create(Book book);

    Book update(Book book);

    Book updateRentPeriod(Long id);

    void delete(Long id);

    boolean exists(Long id);
}
