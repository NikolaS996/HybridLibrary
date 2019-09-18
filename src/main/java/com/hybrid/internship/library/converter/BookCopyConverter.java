package com.hybrid.internship.library.converter;

import com.hybrid.internship.library.dtos.BookCopyDto;
import com.hybrid.internship.library.dtos.BookDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookCopyConverter implements AbstractConverter<BookCopy, BookCopyDto> {

    private BookService bookService;

    @Override
    public BookCopyDto convertToDto(BookCopy bookCopy) {
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

    public BookCopy convertToEntity(BookCopyDto bookCopyDto) {
        Long id = bookCopyDto.getId();
        Book book = bookService.findById(bookCopyDto.getBookDto().getId());

        BookCopy bookCopy = BookCopy.builder()
                .id(id)
                .book(book)
                .build();
        return bookCopy;
    }

}