package com.edgarchirivella.simpleissuetracker.controllers;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
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
        List<Developer> developers = _developerService.GetAll();

        return _developerMapper.toDto(developers);
    }

    @PostMapping
    public DeveloperDetails CreateDeveloper(@RequestBody CreateDeveloperAction action) {
        Developer developer = _developerService.CreateDeveloper(action.name);

        return _developerMapper.toDto(developer);
    }
}
