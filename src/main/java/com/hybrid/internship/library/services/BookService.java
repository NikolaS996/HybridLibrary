package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.Book;

import java.util.List;

public interface BookService extends AbstractService<Book, Long>{

    List<Book> findAllByName(String name);

    List<Book> findAllByAuthor(String author);
}
