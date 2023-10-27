package com.ciphertext.opencarebackend.model.mappers;

import java.util.List;

public interface GenericMapper<T, S> {

    T entityToDto(S entity);

    S dtoToEntity(T dto);

    List<T> entityToDto(List<S> entity);

    List<S> dtoToEntity(List<T> model);

}