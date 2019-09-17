package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.repositories.BookRentalRepository;
import com.hybrid.internship.library.services.BookRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRentalServiceImpl implements BookRentalService {

    @Autowired
    BookRentalRepository bookRentalRepository;
    @Autowired
    BookCopyServiceImpl bookCopyService;
    @Autowired
    ConversionService conversionService;

    @Override
    public List<BookRentalDto> findAll() {
        return bookRentalRepository.findAll().
                stream()
                .map(bookRental -> conversionService.convert(bookRental, BookRentalDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookRentalDto findById(Long id) {
        return conversionService.convert(bookRentalRepository.findById(id).orElseGet(() -> null), BookRentalDto.class);
    }

    @Override
    public BookRentalDto create(BookRentalDto bookRentalDto) {
        //int totalCopies = bookCopyService.totalCopiesByBookId(bookRentalDto.getBookCopyDto().getBookDto().getId());
        Long id = bookRentalDto.getBookCopy().getBookDto().getId();
        int totalCopies =
                bookCopyService.totalCopiesByBookId(id);
        //int rentedCopies = rentedCopies(bookRentalDto.getBookCopyDto().getBookDto().getId());
        int rentedCopies = rentedCopies(id);
        if(totalCopies - rentedCopies > 0)
            return conversionService.convert(bookRentalRepository.save(conversionService.convert(bookRentalDto, BookRental.class)), BookRentalDto.class);
        return null;
    }

    @Override
    public BookRentalDto update(BookRentalDto bookRentalDto) {
        BookRental updatedEntity = conversionService.convert(bookRentalDto, BookRental.class);
        updatedEntity.setIsReturned(true);
        return conversionService.convert(bookRentalRepository.save(updatedEntity), BookRentalDto.class);
    }

    @Override
    public List<BookRentalDto> findAllByUserId(Long id) {
        return bookRentalRepository.findAllByUserId(id)
                .stream()
                .map(bookRental -> conversionService.convert(bookRental, BookRentalDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRentalDto> findAllByBookId(Long id) {
        return bookRentalRepository.findAllByBookId(id)
                .stream()
                .map(bookRental -> conversionService.convert(bookRental, BookRentalDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Book findMostRentedBook() {
        return null;
    }

    @Override
    public int rentedCopies(Long id) {
        return bookRentalRepository.rentedCopies(id);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public boolean exists(Long id) {
        return bookRentalRepository.existsById(id);
    }
}
