package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Developer;

public interface DeveloperService extends CrudService<Developer> {
    Developer updateEntity(Long id, String name);
}
