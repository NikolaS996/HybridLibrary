package com.hybrid.internship.library.services;

import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.models.BookRental;

import java.util.List;

public interface BookRentalService extends AbstractService<BookRental, Long>{

    List<BookRental> findAllByUserId(Long id);

    List<BookRental> findAllByBookId(Long id);

    BookRentalCountDto findMostRentedBook();

    List<BookRental> findOverdueBookReturns();

    List<BookRental> findAllByUserIdAndIsRented(Long id, Boolean bool);

    int rentedCopies(Long id);

    //List<BookRental> isRented(Long id);
}
