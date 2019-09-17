package com.hybrid.internship.library.controllers;

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

//    @Autowired
//    ConversionService conversionService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    BookRentalServiceImpl bookRentalService;

    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks(){
//        return ResponseEntity.ok(
//                bookService.findAll().stream()
//                .map(book -> conversionService.convert(book, BookDto.class))
//                .collect(Collectors.toList())
//        );
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id){
        //return ResponseEntity.ok(conversionService.convert(bookService.findById(id), BookDto.class));
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BookDto>> getBooksByName(@PathVariable("name") String name){
//        return ResponseEntity.ok(
//                bookService.findAllByName(name)
//                .stream()
//                .map(book -> conversionService.convert(book, BookDto.class))
//                .collect(Collectors.toList())
//        );
        return ResponseEntity.ok(bookService.findAllByName(name));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable("author") String author){
//        return ResponseEntity.ok(
//                bookService.findAllByAuthor(author)
//                        .stream()
//                        .map(book -> conversionService.convert(book, BookDto.class))
//                        .collect(Collectors.toList())
//        );
        return ResponseEntity.ok(bookService.findAllByAuthor(author));

    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        //return ResponseEntity.ok(conversionService.convert(bookService.create(bookDto), BookDto.class));
        return ResponseEntity.ok(bookService.create(bookDto));
    }

    @PutMapping()
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        //return ResponseEntity.ok(conversionService.convert(bookService.update(bookDto), BookDto.class));
        return ResponseEntity.ok(bookService.update(bookDto));
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
