package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.repositories.BookRentalRepository;
import com.hybrid.internship.library.services.BookCopyService;
import com.hybrid.internship.library.services.BookRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookRentalServiceImpl implements BookRentalService {

    @Autowired
    private BookRentalRepository bookRentalRepository;
    @Autowired
    private BookCopyService bookCopyService;

    @Override
    public List<BookRental> findAll() {
        return bookRentalRepository.findAll();
    }

    @Override
    public Optional<BookRental> findById(Long id) {
        return bookRentalRepository.findById(id);
    }

    @Override
    public BookRental create(BookRental bookRental) {
        Boolean isBookCopyAvailable = availability(bookRental.getBookCopy().getId());
        if (isBookCopyAvailable)
            return bookRentalRepository.save(bookRental);
        return null;
    }

    @Override
    public BookRental update(BookRental bookRental) {
        bookRental.setIsReturned(true);
        BookRental savedBookRental = bookRentalRepository.save(bookRental);
        log.info("Book rental {} successfully updated.", savedBookRental);
        return savedBookRental;
    }

    @Override
    public List<BookRental> findAllByUserId(Long id) {
        return bookRentalRepository.findAllByUserId(id);
    }

    @Override
    public List<BookRental> findAllByBookId(Long id) {
        return bookRentalRepository.findAllByBookId(id);
    }

    @Override
    public BookRentalCountDto findMostRentedBook() {
        return bookRentalRepository.findMostRentedBook();
    }

    @Override
    public List<BookRental> findOverdueBookReturns() {
        return bookRentalRepository.findOverdueBookReturns();
    }

    @Override
    public List<BookRental> findAllByUserIdAndIsReturned(Long id, Boolean bool) {
        return bookRentalRepository.findAllByUserIdAndIsReturned(id, bool);
    }

    @Override
    public int rentedCopies(Long id) {
        return bookRentalRepository.rentedCopies(id);
    }

    @Override
    public Boolean availability(Long id) {
        return !bookRentalRepository.existsByBookCopyIdAndIsReturned(id, false);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public boolean exists(Long id) {
        return bookRentalRepository.existsById(id);
    }
}
