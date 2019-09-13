package com.hybrid.internship.library.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
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
