package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.BookRentalDto;
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
        return conversionService.convert(bookRentalRepository.save(conversionService.convert(bookRentalDto, BookRental.class)), BookRentalDto.class);
    }

    @Override
    public BookRentalDto update(BookRentalDto bookRentalDto) {
        return conversionService.convert(bookRentalRepository.save(conversionService.convert(bookRentalDto, BookRental.class)), BookRentalDto.class);
    }

    @Override
    public int hasRentedCopies(Long id) {
        return bookRentalRepository.hasRentedCopies(id);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public boolean exists(Long id) {
        return bookRentalRepository.existsById(id);
    }
}
