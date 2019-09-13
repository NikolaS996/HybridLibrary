package com.hybrid.internship.library.dtos;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private Integer rentPeriod;
}
