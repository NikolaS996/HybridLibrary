package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByName(String name);
    List<Book> findAllByAuthor(String author);

}
