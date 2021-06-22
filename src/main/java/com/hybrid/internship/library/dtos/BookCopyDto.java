package com.hybrid.internship.library.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookCopyDto {
    private Long id;
    private BookDto bookDto;
}
