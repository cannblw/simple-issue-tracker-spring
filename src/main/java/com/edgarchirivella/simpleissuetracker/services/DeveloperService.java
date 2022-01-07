package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
import com.edgarchirivella.simpleissuetracker.repositories.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {
    private final DeveloperRepository _developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        _developerRepository = developerRepository;
    }

    public List<Developer> FindAll() {
        return _developerRepository.findAll();
    }

    public Developer CreateDeveloper(String name) {
        var developer = new Developer(name);
        _developerRepository.saveAndFlush(developer);

        return developer;
    }
}
