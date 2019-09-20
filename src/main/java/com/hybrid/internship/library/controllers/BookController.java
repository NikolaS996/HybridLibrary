package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.BookConverter;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.exceptions.RentedCopiesException;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookConverter bookConverter;

    @Autowired
    private BookService bookService;


    @GetMapping()
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.findAll();
        if (books.isEmpty())
            //throw new ResourceNotFoundException("There are no books");
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(
                books.stream()
                        .map(book -> bookConverter.convertToDto(book))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok(bookConverter.convertToDto(book)))
                .orElse(ResponseEntity.notFound().build());
        //.orElseThrow(() -> new ResourceNotFoundException("Book with ID: " + id + " not found."));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BookDto>> getBooksByName(@PathVariable("name") String name) {
        List<Book> books = bookService.findAllByName(name);
        if (books.isEmpty())
            //throw new ResourceNotFoundException("There are no books by name: " + name + ".");
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(books
                .stream()
                .map(book -> bookConverter.convertToDto(book))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable("author") String author) {
        List<Book> books = bookService.findAllByAuthor(author);
        if (books.isEmpty())
            //throw new ResourceNotFoundException("There are no books by author: " + author + ".");
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(books
                .stream()
                .map(book -> bookConverter.convertToDto(book))
                .collect(Collectors.toList())
        );

    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        Book convertedBook = bookConverter.convertToEntity(bookDto);
        return ResponseEntity.ok(bookConverter.convertToDto(bookService.create(convertedBook)));
    }

    @PutMapping()
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        Book convertedBook = bookConverter.convertToEntity(bookDto);
        return ResponseEntity.ok(bookConverter.convertToDto(bookService.create(convertedBook)));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        try {
            bookService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (RentedCopiesException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are rented copies of this book, so it can't be deleted.");
        }
    }

}
