package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    @Query("SELECT id FROM BookCopy WHERE Book.id = ?1")
    List<BookCopy> findAllByBookId(Long id);
}
