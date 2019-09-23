package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.BookRentalConverter;
import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.services.BookRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book-rental")
public class BookRentalController {
    @Autowired
    private BookRentalService bookRentalService;

    @Autowired
    private BookRentalConverter bookRentalConverter;

    @GetMapping
    public ResponseEntity<List<BookRentalDto>> getAllBookRentals() {
        List<BookRental> bookRentals = bookRentalService.findAll();
        if (bookRentals.isEmpty())
            return ResponseEntity.notFound().build();
            //throw new ResourceNotFoundException("There are no book rentals.");
        return ResponseEntity.ok(bookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookRentalDto> getBookRentalById(@PathVariable("id") Long id) {
        return bookRentalService.findById(id)
                .map(bookRental -> ResponseEntity.ok(bookRentalConverter.convertToDto(bookRental)))
                .orElse(ResponseEntity.notFound().build());
        //.orElseThrow(() -> new ResourceNotFoundException("There is no book rental with ID: " + id + "."));
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByUserId(@PathVariable("id") Long id) {
        List<BookRental> bookRentals = bookRentalService.findAllByUserId(id);
        if (bookRentals.isEmpty())
            return ResponseEntity.notFound().build();
            //throw new ResourceNotFoundException("There are no book rentals made by user whose ID is: " + id + ".");
        return ResponseEntity.ok(bookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/book-id/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByBookId(@PathVariable("id") Long id) {
        List<BookRental> bookRentals = bookRentalService.findAllByUserId(id);
        if (bookRentals.isEmpty())
            return ResponseEntity.notFound().build();
            //throw new ResourceNotFoundException("There are rentals of book whose ID is: " + id + ".");
        return ResponseEntity.ok(bookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/most-rented")
    public ResponseEntity<BookRentalCountDto> mostRentedBook() {
        BookRentalCountDto mostRentedBook = bookRentalService.findMostRentedBook();
        if (mostRentedBook == null)
            return ResponseEntity.notFound().build();
            //throw new ResourceNotFoundException("There aren't any rented books");
        return ResponseEntity.ok(mostRentedBook);
    }

    @GetMapping("/overdue-book-returns")
    public ResponseEntity<List<BookRentalDto>> findOverdueBookReturns() {
        List<BookRental> overdueBookRentals = bookRentalService.findOverdueBookReturns();
        if (overdueBookRentals.isEmpty())
            return ResponseEntity.notFound().build();
        //throw new ResourceNotFoundException("There aren't any overdue book returns");
        return ResponseEntity.ok(overdueBookRentals
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }


    @PostMapping("/rent-book")
    public ResponseEntity<BookRentalDto> rentBook(@RequestBody BookRentalDto bookRentalDto) {
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        BookRental savedBook = bookRentalService.create(convertedBookRental);
        if(savedBook == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(bookRentalConverter.convertToDto(savedBook));
    }

    @PutMapping("/return-book")
    public ResponseEntity<BookRentalDto> returnBook(@RequestBody BookRentalDto bookRentalDto) {
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.update(convertedBookRental)));
    }

}
