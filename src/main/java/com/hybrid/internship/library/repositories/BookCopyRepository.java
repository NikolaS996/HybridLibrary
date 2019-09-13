package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
}
