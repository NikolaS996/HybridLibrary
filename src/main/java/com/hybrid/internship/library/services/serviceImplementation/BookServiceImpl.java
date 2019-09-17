package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.repositories.BookRepository;
import com.hybrid.internship.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookCopyServiceImpl bookCopyService;
    @Autowired
    ConversionService conversionService;
    @Autowired
    BookRentalServiceImpl bookRentalService;

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().
                stream()
                .map(book -> conversionService.convert(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        return conversionService.convert(bookRepository.findById(id).orElseGet(() -> null), BookDto.class);
    }

    @Override
    public List<BookDto> findAllByName(String name) {
        return bookRepository.findAllByName(name).
                stream()
                .map(book -> conversionService.convert(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAllByAuthor(String author) {
        return bookRepository.findAllByAuthor(author).
                stream()
                .map(book -> conversionService.convert(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto create(BookDto bookDto) {
        return conversionService.convert(bookRepository.save(conversionService.convert(bookDto, Book.class)), BookDto.class);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return conversionService.convert(bookRepository.save(conversionService.convert(bookDto, Book.class)), BookDto.class);
    }

    @Override
    public void delete(Long id) {
        //if(bookRentalService.hasRentedCopies(id) < 1){
        bookCopyService.deleteAllByBookId(id);
        bookRepository.deleteById(id);
        //}
    }

    @Override
    public boolean exists(Long id) {
        return bookRepository.existsById(id);
    }
}
