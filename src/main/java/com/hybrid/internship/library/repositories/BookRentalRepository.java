package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.models.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*)" +
            "FROM Book JOIN Book_copy ON (Book.id=Book_copy.Book_id) " +
            "JOIN Book_rental ON (Book_copy.id=Book_rental.Book_copy_id) " +
            "WHERE is_returned=FALSE AND Book_id=?1")
    int rentedCopies(Long id);

//    @Query(nativeQuery = true, value = "SELECT COUNT(*)" +
//            "FROM Book_rental JOIN User ON (Book_rental.User_id=User.id" +
//            "WHERE Book_rental.User_id=?1")
    List<BookRental> findAllByUser(Long id);

//    @Query(nativeQuery = true, value = "SELECT COUNT(*)" +
//            "FROM Book_rental JOIN Book ON (Book_rental.Book_id=Book.id" +
//            "WHERE Book_rental.Book_id=?1")
    //List<BookRental> findAllByBook(Long id);

    @Query(nativeQuery = true, value = "SELECT TOP 1 MAX(num) as rental_count, boook, name, author, rent_period FROM " +
            "(SELECT COUNT(Book.id) AS num, Book.id AS boook, name, author, rent_period " +
            "FROM Book JOIN Book_copy ON (Book.id = Book_copy.book_id) " +
            "JOIN Book_rental ON (Book_copy.id = book_rental.book_copy_id) " +
            "GROUP BY boook, name, author, rent_period) " +
            "GROUP BY boook, name, author, rent_period")
    BookRentalCountDto findMostRentedBook();
}
