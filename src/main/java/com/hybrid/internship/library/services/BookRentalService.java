package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.BookRental;

import java.util.List;

public interface BookRentalService extends AbstractService<BookRental, Long>{

    List<BookRental> findAll();

    BookRental findById(Long id);

    BookRental create(BookRental bookRental);

    BookRental update(BookRental bookRental);

    boolean exists(Long id);
}
