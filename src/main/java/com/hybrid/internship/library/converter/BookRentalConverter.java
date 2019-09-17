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
import com.hybrid.internship.library.services.serviceImplementation.BookRentalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

public class BookRentalConverter implements GenericConverter {

    @Autowired
    BookRentalServiceImpl bookRentalService;

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
                .user(UserDto.builder()
                        .id(bookRental.getUser().getId())
                        .firstName(bookRental.getUser().getFirstName())
                        .lastName(bookRental.getUser().getLastName())
                        .email(bookRental.getUser().getEmail())
                        .username(bookRental.getUser().getUsername())
                        .build())
                .bookCopy(BookCopyDto.builder()
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
                        .id(bookRentalDto.getUser().getId())
                        .firstName(bookRentalService
                                .findById(bookRentalDto.getId()).getUser().getFirstName())
                        .lastName(bookRentalService
                                .findById(bookRentalDto.getId()).getUser().getLastName())
                        .username(bookRentalService
                                .findById(bookRentalDto.getId()).getUser().getUsername())
                        .email(bookRentalService
                                .findById(bookRentalDto.getId()).getUser().getEmail())
                        .build())
                .bookCopy(BookCopy.builder()
                        .id(bookRentalDto.getBookCopy().getId())
                        .book(Book.builder()
                                .id(bookRentalDto.getBookCopy().getBookDto().getId())
                                .name(bookRentalService
                                        .findById(bookRentalDto.getId())
                                        .getBookCopy().getBookDto().getName())
                                .author(bookRentalService
                                        .findById(bookRentalDto.getId())
                                        .getBookCopy().getBookDto().getAuthor())
                                .rentPeriod(bookRentalService
                                        .findById(bookRentalDto.getId())
                                        .getBookCopy().getBookDto().getRentPeriod()
                                )
                                .build())
                        .build())
                .rentedDate(bookRentalDto.getRentedDate())
                .build();

        return bookRental;
    }
}
