package com.hybrid.internship.library.converter;

import com.google.common.collect.ImmutableSet;
import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.models.BookCopy;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

public class BookCopyConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(BookCopy.class, BookCopyDto.class)};
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
                .book(bookCopy.getBook())
                .build();

        return bookCopyDto;
    }

    private BookCopy convertBookCopyDto(Object o) {
        BookCopyDto bookCopyDto = (BookCopyDto) o;
        BookCopy bookCopy = BookCopy.builder()
                .id(bookCopyDto.getId())
                .book(bookCopyDto.getBook())
                .build();
        return bookCopy;
    }

}