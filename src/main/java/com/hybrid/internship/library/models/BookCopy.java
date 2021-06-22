package com.hybrid.internship.library.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
