package com.edgarchirivella.simpleissuetracker.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T createEntity(String name);
    void deleteEntity(Long id);
}
