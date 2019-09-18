package com.hybrid.internship.library.services;

import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.models.BookRental;
import java.util.List;

public interface BookRentalService extends AbstractService<BookRental, Long>{

    List<BookRental> findAll();

    BookRental findById(Long id);

    BookRental create(BookRental bookRental);

    BookRental update(BookRental bookRental);

    List<BookRental> findAllByUser(Long id);

    //List<BookRental> findAllByBook(Long id);

    BookRentalCountDto findMostRentedBook();

    int rentedCopies(Long id);

    boolean exists(Long id);
}
