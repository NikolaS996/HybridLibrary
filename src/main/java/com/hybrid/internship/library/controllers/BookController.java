package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.BookConverter;
import com.hybrid.internship.library.converter.UserConverter;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.services.serviceImplementation.BookRentalServiceImpl;
import com.hybrid.internship.library.services.serviceImplementation.BookServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookConverter bookConverter;

    @Autowired
    BookServiceImpl bookService;


    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(
                bookService.findAll().stream()
                .map(book -> bookConverter.convertToDto(book))
                .collect(Collectors.toList())
        );
        //return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookConverter.convertToDto(bookService.findById(id)));
        //return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BookDto>> getBooksByName(@PathVariable("name") String name){
        return ResponseEntity.ok(
                bookService.findAllByName(name)
                .stream()
                .map(book -> bookConverter.convertToDto(book))
                .collect(Collectors.toList())
        );
        //return ResponseEntity.ok(bookService.findAllByName(name));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable("author") String author){
        return ResponseEntity.ok(
                bookService.findAllByAuthor(author)
                        .stream()
                        .map(book -> bookConverter.convertToDto(book))
                        .collect(Collectors.toList())
        );
        //return ResponseEntity.ok(bookService.findAllByAuthor(author));

    }

    @GetMapping("/mostRented")
    public ResponseEntity<BookDto> getMostRentedBook(){
        //return ResponseEntity.ok(bookRentalService.findMostRentedBook());
        return null;
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        //return ResponseEntity.ok(conversionService.convert(bookService.create(bookDto), BookDto.class));
        Book convertedBook = bookConverter.convertToEntity(bookDto);
        return ResponseEntity.ok(bookConverter.convertToDto(bookService.create(convertedBook)));
    }

    @PutMapping()
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        //return ResponseEntity.ok(conversionService.convert(bookService.update(bookDto), BookDto.class));
        Book convertedBook = bookConverter.convertToEntity(bookDto);
        return ResponseEntity.ok(bookConverter.convertToDto(bookService.create(convertedBook)));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){
//        if(bookRentalService.hasRentedCopies(id) < 1) {
        bookService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//        }

        //Izmeniti
        //return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
