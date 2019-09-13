package com.hybrid.internship.library.converter;

import com.google.common.collect.ImmutableSet;
import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.models.BookRental;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

public class BookRentalConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(BookRental.class, BookRentalDto.class)};
        return ImmutableSet.copyOf(pairs);
    }

    @Override
    public Object convert(Object o, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (o instanceof BookRental)
            return convertBookRental(o);
        else if (o instanceof BookRentalDto)
            return convertBookRentalDto(o);
        else
            return null; //Exception
    }

    private BookRentalDto convertBookRental(Object o) {
        BookRental bookRental = (BookRental) o;
        BookRentalDto bookRentalDto = BookRentalDto.builder()
                .id(bookRental.getId())
                .user(bookRental.getUser())
                .bookCopy(bookRental.getBookCopy())
                .rentedDate(bookRental.getRentedDate())
                .build();

        return bookRentalDto;
    }

    private BookRental convertBookRentalDto(Object o) {
        BookRentalDto bookRentalDto = (BookRentalDto) o;
        BookRental bookRental = BookRental.builder()
                .id(bookRentalDto.getId())
                .user(bookRentalDto.getUser())
                .bookCopy(bookRentalDto.getBookCopy())
                .rentedDate(bookRentalDto.getRentedDate())
                .build();

        return bookRental;
    }
}
