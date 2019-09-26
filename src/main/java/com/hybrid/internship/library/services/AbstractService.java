package com.hybrid.internship.library.services;


import java.util.List;
import java.util.Optional;

public interface AbstractService<E, P>{

    List<E> findAll();

    Optional<E> findById(P id);

    E create(E e);

    E update(E e);

    void delete(P id);

    boolean exists(P id);

}
