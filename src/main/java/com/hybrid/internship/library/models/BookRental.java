package com.hybrid.internship.library.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Builder
//Record for every rental
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
