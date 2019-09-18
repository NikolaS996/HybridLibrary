package com.hybrid.internship.library.converter;

import com.google.common.collect.ImmutableSet;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.models.Book;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class BookConverter implements AbstractConverter<Book, BookDto> {

    public BookDto convertToDto(Book book) {
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .rentPeriod(book.getRentPeriod())
                .build();

        return bookDto;
    }

    public Book convertToEntity(BookDto bookDto) {
        Book book = Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .rentPeriod(bookDto.getRentPeriod())
                .build();

        return book;
    }

}