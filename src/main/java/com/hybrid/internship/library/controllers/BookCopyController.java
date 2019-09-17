package com.hybrid.internship.library.controllers;

import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.services.serviceImplementation.BookCopyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookCopy")
public class BookCopyController {
//
//    @Autowired
//    ConversionService conversionService;

    @Autowired
    BookCopyServiceImpl bookCopyService;

    @GetMapping()
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies(){
//        return ResponseEntity.ok(bookCopyService.findAll()
//                .stream()
//                .map(bookCopy -> conversionService.convert(bookCopy, BookCopyDto.class))
//                .collect(Collectors.toList())
//        );

        return ResponseEntity.ok(bookCopyService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookCopyDto> getBookCopyById(@PathVariable("id") Long id){
        //return ResponseEntity.ok(conversionService.convert(bookCopyService.findById(id), BookCopyDto.class));
        return ResponseEntity.ok(bookCopyService.findById(id));
    }

    @GetMapping("/bookId/{id}")
    public ResponseEntity<List<BookCopyDto>> getBookCopiesByBookId(@PathVariable("id") Long id){
//        return ResponseEntity.ok(bookCopyService.findAllByBookId(id)
//                .stream()
//                .map(bookCopy -> conversionService.convert(bookCopy, BookCopyDto.class))
//                .collect(Collectors.toList())
//        );
        return ResponseEntity.ok(bookCopyService.findAllByBookId(id));
    }

    @PostMapping()
    public ResponseEntity<BookCopyDto> createBookCopy(@RequestBody BookCopyDto bookCopyDto){
        return ResponseEntity.ok(bookCopyService.create(bookCopyDto));
    }

    @PutMapping
    public ResponseEntity<BookCopyDto> updateBookCopy(@RequestBody BookCopyDto bookCopyDto){
        return ResponseEntity.ok(bookCopyService.update(bookCopyDto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteBookCopy(@PathVariable("id") Long id){
        bookCopyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
