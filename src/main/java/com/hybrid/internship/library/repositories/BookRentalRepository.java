package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
}
