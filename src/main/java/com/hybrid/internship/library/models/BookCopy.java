package com.hybrid.internship.library.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class BookCopy extends AbstractModel{

    @ManyToOne
    @JoinColumn
    @NotNull
    private Book book;

    public BookCopy(Long id, Book book){
        super(id);
        this.book = book;
    }
}
