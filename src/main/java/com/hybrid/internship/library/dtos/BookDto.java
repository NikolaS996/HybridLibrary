package com.hybrid.internship.library.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private Integer rentPeriod;
}
