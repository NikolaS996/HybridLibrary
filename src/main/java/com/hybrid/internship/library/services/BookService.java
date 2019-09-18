package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.Book;

import java.util.List;

public interface BookService extends AbstractService<Book, Long>{

    List<Book> findAll();

    Book findById(Long id);

    List<Book> findAllByName(String name);

    List<Book> findAllByAuthor(String author);

    Book create(Book book);

    Book update(Book book);

    //BookDto mostRentedBook();

    void delete(Long id);

    boolean exists(Long id);
}
