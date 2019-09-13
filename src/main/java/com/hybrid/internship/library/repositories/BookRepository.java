package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
