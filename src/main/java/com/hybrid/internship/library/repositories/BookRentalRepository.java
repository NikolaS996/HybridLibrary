package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.models.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    @Query(nativeQuery = true, value="SELECT COUNT(*)" +
            "FROM Book JOIN Book_copy ON (Book.id=Book_copy.Book_id) " +
            "JOIN Book_rental ON (Book_copy.id=Book_rental.Book_copy_id) " +
            "WHERE is_returned=FALSE AND Book_id=?1")
    int rentedCopies(Long id);

    @Query(nativeQuery = true, value="SELECT COUNT(*)" +
            "FROM Book_rental JOIN User ON (Book_rental.User_id=User.id" +
            "WHERE Book_rental.User_id=?1")
    List<BookRental> findAllByUserId(Long id);

    @Query(nativeQuery = true, value="SELECT COUNT(*)" +
            "FROM Book_rental JOIN Book ON (Book_rental.Book_id=Book.id" +
            "WHERE Book_rental.Book_id=?1")
    List<BookRental> findAllByBookId(Long id);
}
