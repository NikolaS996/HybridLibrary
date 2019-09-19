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
@RequestMapping("/api/bookRental")
public class BookRentalController {
    @Autowired
    private BookRentalService bookRentalService;

    @Autowired
    private BookRentalConverter bookRentalConverter;

    @GetMapping
    public ResponseEntity<List<BookRentalDto>> getAllBookRentals() {
        return ResponseEntity.ok(bookRentalService.findAll()
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookRentalDto> getBookRentalById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.findById(id)));
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookRentalService.findAllByUserId(id)
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/book-id/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByBookId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookRentalService.findAllByBookId(id)
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/most-rented")
    public ResponseEntity<List<BookRentalCountDto>> mostRentedBook(){
        return ResponseEntity.ok(bookRentalService.findMostRentedBook());
    }

    @GetMapping("/overdue-book-returns")
    public ResponseEntity<List<BookRentalDto>> findOverdueBookReturns(){
        return ResponseEntity.ok(bookRentalService.findOverdueBookReturns()
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }


    @PostMapping("/rent-book")
    public ResponseEntity<BookRentalDto> rentBook(@RequestBody BookRentalDto bookRentalDto) {
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.create(convertedBookRental)));
    }

    @PutMapping("/return-book")
    public ResponseEntity<BookRentalDto> returnBook(@RequestBody BookRentalDto bookRentalDto) {
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.update(convertedBookRental)));
    }

}
