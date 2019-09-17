package com.hybrid.internship.library.services;

import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.models.Book;

import java.util.List;

public interface BookRentalService extends AbstractService<BookRentalDto, Long>{

    List<BookRentalDto> findAll();

    BookRentalDto findById(Long id);

    BookRentalDto create(BookRentalDto bookRental);

    BookRentalDto update(BookRentalDto bookRental);

    List<BookRentalDto> findAllByUserId(Long id);

    List<BookRentalDto> findAllByBookId(Long id);

    Book findMostRentedBook();

    int rentedCopies(Long id);

    boolean exists(Long id);
}
