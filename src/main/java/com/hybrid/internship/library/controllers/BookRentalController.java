package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.BookRentalConverter;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.services.serviceImplementation.BookRentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookRental")
public class BookRentalController {
    @Autowired
    BookRentalServiceImpl bookRentalService;

    @Autowired
    BookRentalConverter bookRentalConverter;

    @GetMapping
    public ResponseEntity<List<BookRentalDto>> getAllBookRentals() {
        //return ResponseEntity.ok(bookRentalService.findAll());
        return ResponseEntity.ok(bookRentalService.findAll()
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookRentalDto> getBookRentalById(@PathVariable("id") Long id) {
        //return ResponseEntity.ok(bookRentalService.findById(id));
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.findById(id)));
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByUserId(@PathVariable("id") Long id) {
        //return ResponseEntity.ok(bookRentalService.findAllByUserId(id));
        return ResponseEntity.ok(bookRentalService.findAllByUser(id)
                .stream()
                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
                .collect(Collectors.toList()));
    }

//    @GetMapping("/bookId/{id}")
//    public ResponseEntity<List<BookRentalDto>> getBookRentalByBookId(@PathVariable("id") Long id) {
//        //return ResponseEntity.ok(bookRentalService.findAllByBookId(id));
//        return ResponseEntity.ok(bookRentalService.findAllByBook(id)
//                .stream()
//                .map(bookRental -> bookRentalConverter.convertToDto(bookRental))
//                .collect(Collectors.toList()));
//    }

    @GetMapping("/mostRented")
    public ResponseEntity<List<BookRentalCountDto>> mostRentedBook(){
        return ResponseEntity.ok(bookRentalService.findMostRentedBook());
    }


    @PostMapping("/rentBook")
    public ResponseEntity<BookRentalDto> rentBook(@RequestBody BookRentalDto bookRentalDto) {
//        return ResponseEntity.ok(bookRentalService.create(bookRentalDto));
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.create(convertedBookRental)));
    }

    @PutMapping("/returnBook")
    public ResponseEntity<BookRentalDto> returnBook(@RequestBody BookRentalDto bookRentalDto) {
//        return ResponseEntity.ok(bookRentalService.update(bookRentalDto));
        BookRental convertedBookRental = bookRentalConverter.convertToEntity(bookRentalDto);
        return ResponseEntity.ok(bookRentalConverter.convertToDto(bookRentalService.update(convertedBookRental)));
    }

}
