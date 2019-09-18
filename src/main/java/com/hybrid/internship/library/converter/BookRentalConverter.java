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
import com.hybrid.internship.library.services.BookCopyService;
import com.hybrid.internship.library.services.BookRentalService;
import com.hybrid.internship.library.services.UserService;
import com.hybrid.internship.library.services.serviceImplementation.BookCopyServiceImpl;
import com.hybrid.internship.library.services.serviceImplementation.BookRentalServiceImpl;
import com.hybrid.internship.library.services.serviceImplementation.BookServiceImpl;
import com.hybrid.internship.library.services.serviceImplementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@AllArgsConstructor
public class BookRentalConverter implements AbstractConverter<BookRental, BookRentalDto> {

    //BookRentalService bookRentalService;
    UserService userService;
    BookCopyService bookCopyService;


    public BookRentalDto convertToDto(BookRental bookRental) {
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
                .isReturned(bookRental.getIsReturned())
                .build();

        return bookRentalDto;
    }

    public BookRental convertToEntity(BookRentalDto bookRentalDto) {
        //BookRental .id .user .bookCopy .rentedDate
        User user = userService.findById(bookRentalDto.getUser().getId());
        BookCopy bookCopy = bookCopyService.findById(bookRentalDto.getBookCopy().getId());
        BookRental bookRental = BookRental.builder()
                .id(bookRentalDto.getId())
                .user(user)
                .bookCopy(bookCopy)
                .rentedDate(bookRentalDto.getRentedDate())
                .isReturned(bookRentalDto.isReturned())
                .build();
//        BookRental bookRental = BookRental.builder()
//                .id(bookRentalDto.getId())
//                .user(User.builder()
//                        .id(bookRentalDto.getUser().getId())
//                        .firstName(bookRentalService
//                                .findById(bookRentalDto.getId()).getUser().getFirstName())
//                        .lastName(bookRentalService
//                                .findById(bookRentalDto.getId()).getUser().getLastName())
//                        .username(bookRentalService
//                                .findById(bookRentalDto.getId()).getUser().getUsername())
//                        .email(bookRentalService
//                                .findById(bookRentalDto.getId()).getUser().getEmail())
//                        .build())
//                .bookCopy(BookCopy.builder()
//                        .id(bookRentalDto.getBookCopy().getId())
//                        .book(Book.builder()
//                                .id(bookRentalDto.getBookCopy().getBookDto().getId())
//                                .name(bookRentalService
//                                        .findById(bookRentalDto.getId())
//                                        .getBookCopy().getBookDto().getName())
//                                .author(bookRentalService
//                                        .findById(bookRentalDto.getId())
//                                        .getBookCopy().getBookDto().getAuthor())
//                                .rentPeriod(bookRentalService
//                                        .findById(bookRentalDto.getId())
//                                        .getBookCopy().getBookDto().getRentPeriod()
//                                )
//                                .build())
//                        .build())
//                .rentedDate(bookRentalDto.getRentedDate())
//                .build();

        return bookRental;
    }
}
