package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
import com.edgarchirivella.simpleissuetracker.exceptions.EntityNotFoundException;
import com.edgarchirivella.simpleissuetracker.repositories.DeveloperRepository;
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
    public List<Developer> findAll() {
        return _developerRepository.findAll();
    }

    @Override
    public Optional<Developer> findById(Long id) {
        return _developerRepository.findById(id);
    }

    @Override
    public Developer createDeveloper(String name) {
        var developer = new Developer(name);
        _developerRepository.saveAndFlush(developer);

        return developer;
    }

    @Override
    public void deleteDeveloper(Long id) {
        _developerRepository.deleteById(id);
    }

    @Override
    public Developer updateDeveloper(Long id, String name) {
        var nullableDeveloper = _developerRepository.findById(id);

        if (nullableDeveloper.isEmpty()) {
            throw new EntityNotFoundException();
        }

        var developer = nullableDeveloper.get();
        developer.setName(name);

        _developerRepository.saveAndFlush(developer);

        return developer;
    }
}
