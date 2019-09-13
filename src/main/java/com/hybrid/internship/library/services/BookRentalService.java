package com.hybrid.internship.library.services;

import com.hybrid.internship.library.models.BookRental;

import java.util.List;

public interface BookRentalService extends AbstractService<BookRental, Long>{

    List<BookRental> getAll();

    BookRental getOne(Long id);

    BookRental create(BookRental bookRental);

    BookRental update(BookRental bookRental);

    //void delete(Long id); // da li je potreban delete?

    boolean exists(Long id);
}
