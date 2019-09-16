package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.repositories.BookRentalRepository;
import com.hybrid.internship.library.services.BookRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRentalServiceImpl implements BookRentalService {

    @Autowired
    BookRentalRepository bookRentalRepository;

    @Override
    public List<BookRental> findAll() {
        return bookRentalRepository.findAll();
    }

    @Override
    public BookRental findById(Long id) {
        return bookRentalRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public BookRental create(BookRental bookRental) {
        return bookRentalRepository.save(bookRental);
    }

    @Override
    public BookRental update(BookRental bookRental) {
        return bookRentalRepository.save(bookRental);
    }

    @Override
    public Integer hasRentedCopies(Long id) {
        return bookRentalRepository.hasRentedCopies(id);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public boolean exists(Long id) {
        return bookRentalRepository.existsById(id);
    }
}
