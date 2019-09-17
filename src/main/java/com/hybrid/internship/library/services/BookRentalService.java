package com.hybrid.internship.library.services;

import com.hybrid.internship.library.dtos.BookRentalDto;

import java.util.List;

public interface BookRentalService extends AbstractService<BookRentalDto, Long>{

    List<BookRentalDto> findAll();

    BookRentalDto findById(Long id);

    BookRentalDto create(BookRentalDto bookRental);

    BookRentalDto update(BookRentalDto bookRental);

    int hasRentedCopies(Long id);

    boolean exists(Long id);
}
