package com.hybrid.internship.library.converter;

import com.google.common.collect.ImmutableSet;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.models.Book;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.time.LocalDate;
import java.util.Set;

public class BookConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(Book.class, BookDto.class)};
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object o, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (o instanceof Book)
            return convertBook(o);
        else if (o instanceof BookDto)
            return convertBookDto(o);
        else
            return null; //Exception
    }

    private BookDto convertBook(Object o) {
        Book book = (Book) o;
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .rentPeriod(book.getRentPeriod())
                .build();

        return bookDto;
    }

    private Book convertBookDto(Object o) {
        BookDto bookDto = (BookDto) o;
        Book book = Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .rentPeriod(bookDto.getRentPeriod())
                .build();

        return book;
    }

}