package com.hybrid.internship.library.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
public class User extends AbstractModel{

    @Column
    @NotBlank(message = "First name can't be blank")
    private String firstName;

    @Column
    @NotBlank(message = "Last name can't be blank")
    private String lastName;

    @Column
    @NotBlank(message = "Username can't be blank")
    private String username;

    @Column
    @Size(min = 8, message = "Password length can't be less than 8 characters")
    private String password;

    @Column
    @Email(message = "Email should be valid")
    private String email;

    @Column
    @NotBlank
    private String role;

    @OneToMany(mappedBy = "books")
    private List<BookRental> rentedCopies;

    //@Builder
    public User(Long id, String firstName, String lastName, String username, String password, String email, String role, List<BookRental> rentedCopies){
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.rentedCopies = rentedCopies;
    }
}
