package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.BookCopyConverter;
import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/bookCopy")
public class BookCopyController {

    @Autowired
    private BookCopyConverter converter;

    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping()
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies() {
        List<BookCopy> bookCopies = bookCopyService.findAll();
        if(bookCopies.isEmpty())
            //throw new ResourceNotFoundException("There are no book copies");
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bookCopies
                .stream()
                .map(bookCopy -> converter.convertToDto(bookCopy))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookCopyDto> getBookCopyById(@PathVariable("id") Long id) {
        //try{
        return bookCopyService.findById(id)
                .map(bookCopy -> ResponseEntity.ok(converter.convertToDto(bookCopy)))
                .orElse(ResponseEntity.notFound().build());
                //.orElseThrow(() -> new ResourceNotFoundException("Book copy with ID: " + id + " not found."));
        //return bookCopyService.findById(id);
        //}
        //catch(BookCopyNotFoundException exception){
        //   return
        //}
    }

    @GetMapping("/book-id/{id}")
    public ResponseEntity<List<BookCopyDto>> getBookCopiesByBookId(@PathVariable("id") Long id) {
        List<BookCopy> bookCopies = bookCopyService.findAllByBookId(id);
        if(bookCopies.isEmpty())
            return ResponseEntity.notFound().build();
            //throw new ResourceNotFoundException("There are no copies of the book whose ID is: " + id + ".");
        return ResponseEntity.ok(bookCopies
                .stream()
                .map(bookCopy -> converter.convertToDto(bookCopy))
                .collect(Collectors.toList())
        );
    }

    @PostMapping()
    public ResponseEntity<BookCopyDto> createBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy convertedBookCopy = converter.convertToEntity(bookCopyDto);
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.create(convertedBookCopy)));
    }

    @PutMapping
    public ResponseEntity<BookCopyDto> updateBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy convertedBookCopy = converter.convertToEntity(bookCopyDto);
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.update(convertedBookCopy)));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable("id") Long id) {
        bookCopyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
