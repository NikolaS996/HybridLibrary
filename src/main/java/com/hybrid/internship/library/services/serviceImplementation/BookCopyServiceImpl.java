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

    @Override
    public List<BookCopy> findAll() {
//        return bookCopyRepository.findAll().
//                stream()
//                .map(bookCopy -> conversionService.convert(bookCopy, BookCopyDto.class))
//                .collect(Collectors.toList());
        return bookCopyRepository.findAll();
    }

    @Override
    public List<BookCopy> findAllByBookId(Long id) {
//        return bookCopyRepository.findAllByBookId(id).
//                stream()
//                .map(bookCopy -> conversionService.convert(bookCopy, BookCopyDto.class))
//                .collect(Collectors.toList());
        return bookCopyRepository.findAllByBookId(id);
    }

    @Override
    public BookCopy findById(Long id) {
        //return conversionService.convert(bookCopyRepository.findById(id).orElseGet(() -> null), BookCopyDto.class);
        return bookCopyRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public BookCopy create(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
        //return conversionService.convert(bookCopyRepository.save(conversionService.convert(bookCopyDto, BookCopy.class)), BookCopyDto.class);
    }

    @Override
    public BookCopy update(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
        //return conversionService.convert(bookCopyRepository.save(conversionService.convert(bookCopyDto, BookCopy.class)), BookCopyDto.class);
    }

    @Override
    public int totalCopiesByBookId(Long id) {
        return bookCopyRepository.totalCopiesByBookId(id);
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
