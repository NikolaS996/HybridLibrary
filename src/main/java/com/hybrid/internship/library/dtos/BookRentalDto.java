package com.hybrid.internship.library.dtos;

import com.hybrid.internship.library.models.BookCopy;
import com.hybrid.internship.library.models.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookRentalDto {
    private Long id;
    private User user;
    private BookCopy bookCopy;
    private LocalDate rentedDate;
    //private boolean isReturned;
}
