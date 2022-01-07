package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {
    List<Developer> findAll();
    Optional<Developer> findById(Long id);
    Developer createDeveloper(String name);
    void DeleteDeveloper(Long id);
    Developer updateDeveloper(Long id, String name);
}
