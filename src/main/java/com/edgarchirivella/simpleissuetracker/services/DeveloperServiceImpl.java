package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
import com.edgarchirivella.simpleissuetracker.repositories.DeveloperRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository _developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        _developerRepository = developerRepository;
    }

    @Override
    public List<Developer> FindAll() {
        return _developerRepository.findAll();
    }

    @Override
    public Optional<Developer> FindById(Long id) {
        return _developerRepository.findById(id);
    }

    @Override
    public Developer CreateDeveloper(String name) {
        var developer = new Developer(name);
        _developerRepository.saveAndFlush(developer);

        return developer;
    }

    @Override
    public void DeleteDeveloper(Long id) {
        _developerRepository.deleteById(id);
    }
}
