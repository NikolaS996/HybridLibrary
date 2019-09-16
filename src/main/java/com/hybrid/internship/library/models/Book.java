package com.hybrid.internship.library.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper=false)
public class Book extends AbstractModel {

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String author;

    @Column
    @NotNull
    private Integer rentPeriod;

    @OneToMany(mappedBy = "book")
    private List<BookCopy> rentedCopies;

    @Builder
    public Book(Long id, String name, String author, Integer rentPeriod, List<BookCopy> rentedCopies){
        super(id);
        this.name = name;
        this.author = author;
        this.rentPeriod = rentPeriod;
        this.rentedCopies = rentedCopies;
    }
}
