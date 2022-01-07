package com.edgarchirivella.simpleissuetracker.controllers;

import com.edgarchirivella.simpleissuetracker.dto.actions.UpdateDeveloperAction;
import com.edgarchirivella.simpleissuetracker.mappers.DeveloperMapper;
import com.edgarchirivella.simpleissuetracker.services.DeveloperService;
import com.edgarchirivella.simpleissuetracker.dto.actions.CreateDeveloperAction;
import com.edgarchirivella.simpleissuetracker.dto.details.DeveloperDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("developers")
public class DeveloperController {
    private final DeveloperService _developerService;
    private final DeveloperMapper _developerMapper;

    public DeveloperController(
            DeveloperService developerService,
            DeveloperMapper developerMapper) {
        _developerService = developerService;
        _developerMapper = developerMapper;
    }

    @GetMapping
    public List<DeveloperDetails> getAll() {
        log.info("Getting all developers");

        var developers = _developerService.findAll();

        return _developerMapper.toDto(developers);
    }

    @GetMapping("/{id}")
    public DeveloperDetails getById(@PathVariable Long id) {
        log.info("Getting developer with id {}", id);

        var developer = _developerService.findById(id);

        if (developer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return _developerMapper.toDto(developer.get());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Deleting developer with id {}", id);

        _developerService.deleteDeveloper(id);
    }

    @PutMapping("/{id}")
    public DeveloperDetails updateDeveloper(@RequestBody UpdateDeveloperAction action, @PathVariable Long id) {
        log.info("Updating developer with id {}", id);

        var developers = _developerService.updateDeveloper(id, action.getName());

        return _developerMapper.toDto(developers);
    }

    @PostMapping
    public DeveloperDetails createDeveloper(@RequestBody CreateDeveloperAction action) {
        log.info("Creating developer {}", action.getName());

        var developer = _developerService.createDeveloper(action.getName());

        return _developerMapper.toDto(developer);
    }
}
