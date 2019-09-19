package com.hybrid.internship.library.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book extends AbstractModel {

    @Column
    @NotBlank(message = "Name can't be blank")
    private String name;

    @Column
    @NotBlank(message = "Author can't be blank")
    private String author;

    @Column
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
