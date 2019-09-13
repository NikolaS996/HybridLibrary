package com.hybrid.internship.library.dtos;

import com.hybrid.internship.library.models.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCopyDto {
    private Long id;
    private Book book;
}
