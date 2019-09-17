package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.repositories.BookCopyRepository;
import com.hybrid.internship.library.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    ConversionService conversionService;

    @Override
    public List<BookCopyDto> findAll() {
        return bookCopyRepository.findAll().
                stream()
                .map(bookCopy -> conversionService.convert(bookCopy, BookCopyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookCopyDto> findAllByBookId(Long id) {
        return bookCopyRepository.findAllByBookId(id).
                stream()
                .map(bookCopy -> conversionService.convert(bookCopy, BookCopyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookCopyDto findById(Long id) {
        return conversionService.convert(bookCopyRepository.findById(id).orElseGet(() -> null), BookCopyDto.class);
    }

    @Override
    public BookCopyDto create(BookCopyDto bookCopyDto) {
        return conversionService.convert(bookCopyRepository.save(conversionService.convert(bookCopyDto, BookCopy.class)), BookCopyDto.class);
    }

    @Override
    public BookCopyDto update(BookCopyDto bookCopyDto) {
        return conversionService.convert(bookCopyRepository.save(conversionService.convert(bookCopyDto, BookCopy.class)), BookCopyDto.class);
    }

    @Override
    public void delete(Long id) {
        bookCopyRepository.deleteById(id);
    }

    @Override
    public void deleteAllByBookId(Long id) {
        bookCopyRepository.deleteAllByBookId(id);
    }

    @Override
    public boolean exists(Long id) {
        return bookCopyRepository.existsById(id);
    }
}
