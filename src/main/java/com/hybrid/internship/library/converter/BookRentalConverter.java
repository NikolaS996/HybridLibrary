package com.hybrid.internship.library.converter;

import com.google.common.collect.ImmutableSet;
import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.models.User;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

public class BookRentalConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        ConvertiblePair[] pairs = new ConvertiblePair[]{
                new ConvertiblePair(BookRental.class, BookRentalDto.class),
                new ConvertiblePair(BookRentalDto.class, BookRental.class)};
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
                .userDto(UserDto.builder()
                    .id(bookRental.getUser().getId())
                    .firstName(bookRental.getUser().getFirstName())
                    .lastName(bookRental.getUser().getLastName())
                    .email(bookRental.getUser().getEmail())
                    .username(bookRental.getUser().getUsername())
                    .build())
                .bookCopyDto(BookCopyDto.builder()
                    .id(bookRental.getBookCopy().getId())
                    .bookDto(BookDto.builder()
                        .id(bookRental.getBookCopy().getBook().getId())
                        .name(bookRental.getBookCopy().getBook().getName())
                        .author(bookRental.getBookCopy().getBook().getAuthor())
                        .rentPeriod(bookRental.getBookCopy().getBook().getRentPeriod())
                        .build())
                    .build())
                .rentedDate(bookRental.getRentedDate())
                .build();

        return bookRentalDto;
    }

    private BookRental convertBookRentalDto(Object o) {
        BookRentalDto bookRentalDto = (BookRentalDto) o;
        BookRental bookRental = BookRental.builder()
                .id(bookRentalDto.getId())
                .user(User.builder()
                    .id(bookRentalDto.getUserDto().getId())
                    .firstName(bookRentalDto.getUserDto().getFirstName())
                    .lastName(bookRentalDto.getUserDto().getLastName())
                    .username(bookRentalDto.getUserDto().getUsername())
                    .email(bookRentalDto.getUserDto().getEmail())
                    .build())
                .bookCopy(BookCopy.builder()
                    .id(bookRentalDto.getBookCopyDto().getId())
                    .book(Book.builder()
                        .id(bookRentalDto.getBookCopyDto().getBookDto().getId())
                        .name(bookRentalDto.getBookCopyDto().getBookDto().getName())
                        .author(bookRentalDto.getBookCopyDto().getBookDto().getAuthor())
                        .rentPeriod(bookRentalDto.getBookCopyDto().getBookDto().getRentPeriod())
                        .build())
                    .build())
                .rentedDate(bookRentalDto.getRentedDate())
                .build();

        return bookRental;
    }
}
