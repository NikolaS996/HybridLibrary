package com.hybrid.internship.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRentalCountDto {

    private Integer rental_count;
    private Long boook;
    private String name;
    private String author;
    private Integer rent_period;
}
