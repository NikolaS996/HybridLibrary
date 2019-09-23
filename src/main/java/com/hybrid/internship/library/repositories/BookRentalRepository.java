package com.hybrid.internship.library.repositories;

import com.hybrid.internship.library.dtos.BookRentalCountDto;
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


    List<BookRental> findAllByUserId(Long id);
    List<BookRental> findAllByUserIdAndIsReturned(Long id, Boolean bool);

    @Query(nativeQuery = true, value="SELECT Book_rental.id, Book_rental.is_returned, Book_rental.rented_date," +
            "Book_rental.book_copy_id, Book_rental.user_id " +
            "FROM Book_rental JOIN Book_copy ON (Book_rental.Book_copy_id = Book_copy.id)" +
            "WHERE Book_copy.book_id = ?1")
    List<BookRental> findAllByBookId(Long id);

    @Query(nativeQuery = true, value = "SELECT TOP 1 MAX(num) as rentalCount, book, name, author, rentPeriod FROM " +
            "(SELECT COUNT(Book.id) AS num, Book.id AS book, name, author, rent_period as rentPeriod " +
            "FROM Book JOIN Book_copy ON (Book.id = Book_copy.book_id) " +
            "JOIN Book_rental ON (Book_copy.id = book_rental.book_copy_id) " +
            "GROUP BY book, name, author, rentPeriod)" +
            "GROUP BY book, name, author, rentPeriod")
    BookRentalCountDto findMostRentedBook();

    @Query(nativeQuery = true, value="SELECT br.id, br.rented_date, br.is_returned, br.book_copy_id, br.user_id " +
            "FROM Book b JOIN Book_copy bc ON (b.id=bc.book_id) JOIN book_rental br ON (br.book_copy_id=bc.id) " +
            "WHERE is_returned = FALSE AND(rented_date < CURRENT_DATE() - rent_period)")
    List<BookRental> findOverdueBookReturns();

    Boolean existsByBookCopyIdAndIsReturned(Long id, Boolean bool);

    //List<BookRental> findByBookCopyIdAndIsReturned(Long id, Boolean bool);
}
