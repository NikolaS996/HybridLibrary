package com.hybrid.internship.library.converter;

public interface AbstractConverter<E, D> {

    E convertToEntity(D dto);

    D convertToDto(E entity);
}
