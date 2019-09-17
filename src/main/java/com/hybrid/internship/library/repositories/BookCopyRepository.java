package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    List<BookCopy> findAllByBookId(Long id);
    void deleteAllByBookId(Long id);
    @Query(nativeQuery = true, value="SELECT COUNT(*)" +
            "FROM Book_copy WHERE book_id=?1")
    int totalCopiesByBookId(Long id);
}
