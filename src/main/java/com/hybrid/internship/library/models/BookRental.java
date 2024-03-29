package com.hybrid.internship.library.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRental extends AbstractModel{

    @JoinColumn
    @ManyToOne
    @NotNull
    private User user;

    @JoinColumn
    @ManyToOne
    @NotNull
    private BookCopy bookCopy;

    @Column
    @NotNull
    private LocalDate rentedDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean isReturned;

    @Builder
    public BookRental(Long id, User user, BookCopy bookCopy, LocalDate rentedDate, boolean isReturned){
        super(id);
        this.user = user;
        this.bookCopy = bookCopy;
        this.rentedDate = rentedDate;
        this.isReturned = isReturned;
    }
}
