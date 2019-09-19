package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.repositories.BookRentalRepository;
import com.hybrid.internship.library.services.BookCopyService;
import com.hybrid.internship.library.services.BookRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public BookRental findById(Long id) {
        return bookRentalRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public BookRental create(BookRental bookRental) {
        Long id = bookRental.getBookCopy().getBook().getId();
        int totalCopies =
                bookCopyService.totalCopiesByBookId(id);
        int rentedCopies = rentedCopies(id);
        if(totalCopies - rentedCopies > 0){
            return bookRentalRepository.save(bookRental);
        }
        return null;
    }

    @Override
    public BookRental update(BookRental bookRental) {
        bookRental.setIsReturned(true);
        return bookRentalRepository.save(bookRental);
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
    public List<BookRentalCountDto> findMostRentedBook() {
        return bookRentalRepository.findMostRentedBook();
    }

    @Override
    public List<BookRental> findOverdueBookReturns() {
        return bookRentalRepository.findOverdueBookReturns();
    }

    @Override
    public int rentedCopies(Long id) {
        return bookRentalRepository.rentedCopies(id);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public boolean exists(Long id) {
        return bookRentalRepository.existsById(id);
    }
}
