package com.hybrid.internship.library.services;


import java.util.List;

public interface AbstractService<E, P>{

    List<E> getAll();

    E getOne(P id);

    E create(E e);

    E update(E e);

    void delete(P id); //void?

    boolean exists(P id);

}
