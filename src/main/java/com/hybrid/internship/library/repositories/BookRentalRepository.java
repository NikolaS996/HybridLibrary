package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    @Query(nativeQuery = true, value="SELECT COUNT(*)" +
            "FROM Book JOIN Book_copy ON (Book.id=Book_copy.Book_id) " +
            "JOIN Book_rental ON (Book_copy.id=Book_rental.Book_copy_id) " +
            "WHERE is_returned=FALSE")
    Integer hasRentedCopies(Long id);
}
