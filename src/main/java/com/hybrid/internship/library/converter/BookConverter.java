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
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setRentPeriod(book.getRentPeriod());

        return bookDto;
    }

    private Book convertBookDto(Object o) {
        BookDto bookDto = (BookDto) o;
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setRentPeriod(bookDto.getRentPeriod());

        return book;
    }

}