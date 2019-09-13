package com.hybrid.internship.library.dtos;

import com.hybrid.internship.library.models.Book;
import lombok.Data;

@Data
public class BookCopyDto {
    private Long id;
    private Book book;
}
