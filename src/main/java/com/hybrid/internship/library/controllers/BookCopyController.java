package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.converter.BookCopyConverter;
import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.services.serviceImplementation.BookCopyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookCopy")
public class BookCopyController {

    @Autowired
    BookCopyConverter converter;

    @Autowired
    BookCopyServiceImpl bookCopyService;

    @GetMapping()
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies(){
        return ResponseEntity.ok(bookCopyService.findAll()
                .stream()
                .map(bookCopy -> converter.convertToDto(bookCopy))
                .collect(Collectors.toList())
        );

        //return ResponseEntity.ok(bookCopyService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookCopyDto> getBookCopyById(@PathVariable("id") Long id){
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.findById(id)));
        //return ResponseEntity.ok(bookCopyService.findById(id));
    }

    @GetMapping("/bookId/{id}")
    public ResponseEntity<List<BookCopyDto>> getBookCopiesByBookId(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookCopyService.findAllByBookId(id)
                .stream()
                .map(bookCopy -> converter.convertToDto(bookCopy))
                .collect(Collectors.toList())
        );
        //return ResponseEntity.ok(bookCopyService.findAllByBookId(id));
    }

    @PostMapping()
    public ResponseEntity<BookCopyDto> createBookCopy(@RequestBody BookCopyDto bookCopyDto){
        //return ResponseEntity.ok(bookCopyService.create(bookCopyDto));
        BookCopy convertedBookCopy = converter.convertToEntity(bookCopyDto);
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.create(convertedBookCopy)));
    }

    @PutMapping
    public ResponseEntity<BookCopyDto> updateBookCopy(@RequestBody BookCopyDto bookCopyDto){
        //return ResponseEntity.ok(bookCopyService.update(bookCopyDto));
        BookCopy convertedBookCopy = converter.convertToEntity(bookCopyDto);
        return ResponseEntity.ok(converter.convertToDto(bookCopyService.update(convertedBookCopy)));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable("id") Long id){
        bookCopyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
