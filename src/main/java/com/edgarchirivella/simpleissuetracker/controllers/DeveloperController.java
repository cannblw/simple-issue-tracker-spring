package com.edgarchirivella.simpleissuetracker.controllers;

import com.edgarchirivella.simpleissuetracker.mappers.DeveloperMapper;
import com.edgarchirivella.simpleissuetracker.services.DeveloperService;
import com.edgarchirivella.simpleissuetracker.dto.actions.CreateDeveloperAction;
import com.edgarchirivella.simpleissuetracker.dto.details.DeveloperDetails;
import org.springframework.web.bind.annotation.*;

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
    public List<DeveloperDetails> GetAll() {
        var developers = _developerService.FindAll();

        return _developerMapper.toDto(developers);
    }

    @PostMapping
    public DeveloperDetails CreateDeveloper(@RequestBody CreateDeveloperAction action) {
        var developer = _developerService.CreateDeveloper(action.name);

        return _developerMapper.toDto(developer);
    }
}
