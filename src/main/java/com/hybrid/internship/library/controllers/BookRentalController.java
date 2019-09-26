package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.BookRentalConverter;
import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.services.BookRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/book-rental")
public class BookRentalController {
    @Autowired
    private BookRentalService bookRentalService;

    @Autowired
    private BookRentalConverter bookRentalConverter;

    @GetMapping
    public ResponseEntity<List<BookRentalDto>> getAllBookRentals() {
        List<BookRental> bookRentals = bookRentalService.findAll();
        if (bookRentals.isEmpty()) {
            log.info("Currently there are no book rentals to fetch.");
            return ResponseEntity.notFound().build();
        }
        //throw new ResourceNotFoundException("There are no book rentals.");
        log.info("Book rentals are fetched.");
        return ResponseEntity.ok(bookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookRentalDto> getBookRentalById(@PathVariable("id") Long id) {
        return bookRentalService.findById(id)
                .map(bookRental -> {
                    log.info("Book rental {} is fetched.", bookRental);
                    return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRental));
                })
                .orElse(ResponseEntity.notFound().build());
        //.orElseThrow(() -> new ResourceNotFoundException("There is no book rental with ID: " + id + "."));
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByUserId(@PathVariable("id") Long id) {
        List<BookRental> bookRentals = bookRentalService.findAllByUserId(id);
        if (bookRentals.isEmpty()) {
            log.info("User with ID {} hasn't rented any books.", id);
            return ResponseEntity.notFound().build();
        }
        //throw new ResourceNotFoundException("There are no book rentals made by user whose ID is: " + id + ".");
        log.info("Book rentals made by user with ID {} are fetched.", id);
        return ResponseEntity.ok(bookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/book-id/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByBookId(@PathVariable("id") Long id) {
        List<BookRental> bookRentals = bookRentalService.findAllByBookId(id);
        if (bookRentals.isEmpty()) {
            log.info("There are no rentals of the book with ID {} to be fetched.", id);
            return ResponseEntity.notFound().build();
        }
        //throw new ResourceNotFoundException("There are rentals of book whose ID is: " + id + ".");
        log.info("Rentals of the book with ID {} are fetched.", id);
        return ResponseEntity.ok(bookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/most-rented")
    public ResponseEntity<BookRentalCountDto> mostRentedBook() {
        BookRentalCountDto mostRentedBook = bookRentalService.findMostRentedBook();
        if (mostRentedBook == null) {
            log.info("There isn't any book to be fetched as the most rented one.");
            return ResponseEntity.notFound().build();
        }
        //throw new ResourceNotFoundException("There aren't any rented books");
        log.info("Most rented book {} is fetched.", mostRentedBook);
        return ResponseEntity.ok(mostRentedBook);
    }

    @GetMapping("/overdue-book-returns")
    public ResponseEntity<List<BookRentalDto>> findOverdueBookReturns() {
        List<BookRental> overdueBookRentals = bookRentalService.findOverdueBookReturns();
        if (overdueBookRentals.isEmpty()) {
            log.info("There aren't any rented books that should be returned, but aren't.");
            return ResponseEntity.notFound().build();
        }
        //throw new ResourceNotFoundException("There aren't any overdue book returns");
        log.info("Overdue book returns {} are fetched.", overdueBookRentals);
        return ResponseEntity.ok(overdueBookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }


    @PostMapping("/rent-book")
    public ResponseEntity<BookRentalDto> rentBook(@RequestBody BookRentalDto bookRentalDto) {
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        BookRental savedBookRental = bookRentalService.create(convertedBookRental);
        if (savedBookRental == null) {
            log.info("Requested book copy isn't currently available.");
            return ResponseEntity.badRequest().build();
        }
        log.info("Requested book rental {} is successfully created.", savedBookRental);
        return ResponseEntity.ok(bookRentalConverter.convertToDto(savedBookRental));
    }

    @PutMapping("/return-book")
    public ResponseEntity<BookRentalDto> returnBook(@RequestBody BookRentalDto bookRentalDto) {
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.update(convertedBookRental)));
    }

}
