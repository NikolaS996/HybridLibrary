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

//import org.springframework.core.convert.converter;

@RestController
@RequestMapping("/api/bookCopy")
public class BookCopyController {

    @Autowired
    private BookCopyConverter converter;

    @Autowired
    private BookCopyService bookCopyService;

    @GetMapping()
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies(){
        return ResponseEntity.ok(bookCopyService.findAll()
                .stream()
                .map(bookCopy -> converter.convertToDto(bookCopy))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookCopyDto> getBookCopyById(@PathVariable("id") Long id){
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.findById(id)));
    }

    @GetMapping("/book-id/{id}")
    public ResponseEntity<List<BookCopyDto>> getBookCopiesByBookId(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookCopyService.findAllByBookId(id)
                .stream()
                .map(bookCopy -> converter.convertToDto(bookCopy))
                .collect(Collectors.toList())
        );
    }

    @PostMapping()
    public ResponseEntity<BookCopyDto> createBookCopy(@RequestBody BookCopyDto bookCopyDto){
        BookCopy convertedBookCopy = converter.convertToEntity(bookCopyDto);
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.create(convertedBookCopy)));
    }

    @PutMapping
    public ResponseEntity<BookCopyDto> updateBookCopy(@RequestBody BookCopyDto bookCopyDto){
        BookCopy convertedBookCopy = converter.convertToEntity(bookCopyDto);
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.update(convertedBookCopy)));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable("id") Long id){
        bookCopyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
