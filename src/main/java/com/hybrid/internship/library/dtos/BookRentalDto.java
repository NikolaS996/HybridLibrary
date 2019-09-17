package com.hybrid.internship.library.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookRentalDto {
    private Long id;
    private UserDto userDto;
    private BookCopyDto bookCopyDto;
    private LocalDate rentedDate;
    //private boolean isReturned;
}
