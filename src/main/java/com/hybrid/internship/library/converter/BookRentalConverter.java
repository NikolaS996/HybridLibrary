package com.hybrid.internship.library.converter;

import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.dtos.UserDto;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.models.User;
import com.hybrid.internship.library.services.BookCopyService;
import com.hybrid.internship.library.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookRentalConverter implements AbstractConverter<BookRental, BookRentalDto> {

    @Autowired
    private UserService userService;
    @Autowired
    private BookCopyService bookCopyService;


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
        User user = userService.findById(bookRentalDto.getUser().getId());
        BookCopy bookCopy = bookCopyService.findById(bookRentalDto.getBookCopy().getId());
        BookRental bookRental = BookRental.builder()
                .id(bookRentalDto.getId())
                .user(user)
                .bookCopy(bookCopy)
                .rentedDate(bookRentalDto.getRentedDate())
                .isReturned(bookRentalDto.isReturned())
                .build();

        return bookRental;
    }
}
