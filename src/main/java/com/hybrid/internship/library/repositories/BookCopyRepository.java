package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    List<BookCopy> findAllByBookId(Long id);
    void deleteAllByBookId(Long id);
}
