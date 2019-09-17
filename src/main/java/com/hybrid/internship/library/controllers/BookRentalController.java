package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.services.serviceImplementation.BookRentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookRental")
public class BookRentalController {
    @Autowired
    BookRentalServiceImpl bookRentalService;

    @GetMapping
    public ResponseEntity<List<BookRentalDto>> getAllBookRentals()
    {
        return ResponseEntity.ok(bookRentalService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookRentalDto> getBookRentalById(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookRentalService.findById(id));
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookRentalService.findAllByUserId(id));
    }

    @GetMapping("/bookId/{id}")
    public ResponseEntity<List<BookRentalDto>> getBookRentalByBookId(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookRentalService.findAllByBookId(id));
    }

    //@GetMapping("/mostRented")


    @PostMapping("/rentBook")
    public ResponseEntity<BookRentalDto> rentBook(@RequestBody BookRentalDto bookRentalDto){
        return ResponseEntity.ok(bookRentalService.create(bookRentalDto));
    }

    @PutMapping("/returnBook")
    public ResponseEntity<BookRentalDto> returnBook(@RequestBody BookRentalDto bookRentalDto){
        return ResponseEntity.ok(bookRentalService.update(bookRentalDto));
    }

}
