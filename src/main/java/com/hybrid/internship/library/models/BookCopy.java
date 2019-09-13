package com.hybrid.internship.library.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Builder
public class BookCopy extends AbstractModel{

    @ManyToOne
    @JoinColumn
    @NotNull
    private Book book;

    @Builder
    public BookCopy(Long id, Book book){
        super(id);
        this.book = book;
    }
}
