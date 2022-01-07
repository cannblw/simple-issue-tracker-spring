package com.edgarchirivella.simpleissuetracker.controllers;

import com.edgarchirivella.simpleissuetracker.dto.actions.UpdateDeveloperAction;
import com.edgarchirivella.simpleissuetracker.mappers.DeveloperMapper;
import com.edgarchirivella.simpleissuetracker.services.DeveloperService;
import com.edgarchirivella.simpleissuetracker.dto.actions.CreateDeveloperAction;
import com.edgarchirivella.simpleissuetracker.dto.details.DeveloperDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        var developers = _developerService.findAll();

        return _developerMapper.toDto(developers);
    }

    @GetMapping("/{id}")
    public DeveloperDetails getById(@PathVariable Long id) {
        var developer = _developerService.findById(id);

        if (developer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return _developerMapper.toDto(developer.get());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        _developerService.DeleteDeveloper(id);
    }

    @PutMapping("/{id}")
    public DeveloperDetails updateDeveloper(@RequestBody UpdateDeveloperAction action, @PathVariable Long id) {
        var developers = _developerService.updateDeveloper(id, action.name);

        return _developerMapper.toDto(developers);
    }

    @PostMapping
    public DeveloperDetails createDeveloper(@RequestBody CreateDeveloperAction action) {
        var developer = _developerService.createDeveloper(action.name);

        return _developerMapper.toDto(developer);
    }
}
