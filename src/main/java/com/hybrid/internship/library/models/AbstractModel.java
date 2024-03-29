package com.hybrid.internship.library.models;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
