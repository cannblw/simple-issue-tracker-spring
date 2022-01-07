package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {
    List<Developer> FindAll();
    Optional<Developer> FindById(Long id);
    Developer CreateDeveloper(String name);
    void DeleteDeveloper(Long id);
    Developer UpdateDeveloper(Long id, String name);
}
