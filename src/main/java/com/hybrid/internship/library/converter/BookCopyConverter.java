package com.hybrid.internship.library.converter;

import com.google.common.collect.ImmutableSet;
import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.services.serviceImplementation.BookCopyServiceImpl;
import com.hybrid.internship.library.services.serviceImplementation.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

public class BookCopyConverter implements GenericConverter {

    @Autowired
    BookCopyServiceImpl bookCopyService;
    @Autowired
    BookServiceImpl bookService;

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(BookCopy.class, BookCopyDto.class),
                new ConvertiblePair(BookCopyDto.class, BookCopy.class)};
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object o, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (o instanceof BookCopy)
            return convertBookCopy(o);
        else if (o instanceof BookCopyDto)
            return convertBookCopyDto(o);
        else
            return null; //Exception
    }

    private BookCopyDto convertBookCopy(Object o) {
        BookCopy bookCopy = (BookCopy) o;
        BookCopyDto bookCopyDto = BookCopyDto.builder()
                .id(bookCopy.getId())
                .bookDto(BookDto.builder()
                        .id(bookCopy.getBook().getId())
                        .name(bookCopy.getBook().getName())
                        .author(bookCopy.getBook().getAuthor())
                        .rentPeriod(bookCopy.getBook().getRentPeriod())
                        .build())
                .build();

        return bookCopyDto;
    }

    private BookCopy convertBookCopyDto(Object o) {
        BookCopyDto bookCopyDto = (BookCopyDto) o;
        Book book = bookService.findById(bookCopyDto.getBookDto().getId());

        BookCopy bookCopy = BookCopy.builder()
                .id(bookCopyDto.getId())
                .book(book)
                .build();
        return bookCopy;
    }

}