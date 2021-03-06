package com.edgarchirivella.simpleissuetracker.controllers;


import com.edgarchirivella.simpleissuetracker.dto.actions.CreateBugAction;
import com.edgarchirivella.simpleissuetracker.dto.actions.CreateStoryAction;
import com.edgarchirivella.simpleissuetracker.dto.actions.UpdateBugAction;
import com.edgarchirivella.simpleissuetracker.dto.actions.UpdateStoryAction;
import com.edgarchirivella.simpleissuetracker.dto.details.StoryDetails;
import com.edgarchirivella.simpleissuetracker.dto.details.TicketDetails;
import com.edgarchirivella.simpleissuetracker.mappers.TicketMapper;
import com.edgarchirivella.simpleissuetracker.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("tickets")
public class TicketController {
    private final TicketService _ticketService;
    private final TicketMapper _ticketMapper;

    public TicketController(
            TicketService ticketService,
            TicketMapper ticketMapper) {
        _ticketService = ticketService;
        _ticketMapper = ticketMapper;
    }

    @GetMapping
    public List<TicketDetails> getAllTickets() {
        log.info("Getting all tickets");

        var tickets = _ticketService.findAll();

        return _ticketMapper.ticketToDto(tickets);
    }

    @PostMapping("/stories")
    public TicketDetails createStory(@Valid @RequestBody CreateStoryAction action) {
        log.info("Creating story {}", action.getTitle());

        var story = _ticketService.createStory(
                action.getTitle(),
                action.getDescription(),
                action.getPoints());

        return _ticketMapper.storyToDto(story);
    }

    @PutMapping("/stories/{id}")
    public TicketDetails updateStory(@PathVariable Long id, @RequestBody UpdateStoryAction action) {
        log.info("Updating story with id {}", id);

        var story = _ticketService.updateStory(
                id,
                action.getTitle(),
                action.getDescription(),
                action.getPoints());

        return _ticketMapper.storyToDto(story);
    }

    @DeleteMapping("/stories/{id}")
    public void deleteStoryById(@PathVariable Long id) {
        log.info("Deleting story with id {}", id);

        _ticketService.deleteStory(id);
    }

    @PostMapping("/stories/{id}/assign/{developerId}")
    public TicketDetails assignStoryToDeveloper(@PathVariable("id") Long storyId, @PathVariable Long developerId) {
        log.info("Assigning story {} to developer {}", storyId, developerId);

        var story = _ticketService.assignStoryToDeveloper(
                storyId,
                developerId);

        return _ticketMapper.storyToDto(story);
    }

    @PostMapping("/bugs")
    public TicketDetails createBug(@Valid @RequestBody CreateBugAction action) {
        log.info("Creating bug {}", action.getTitle());

        var bug = _ticketService.createBug(
                action.getTitle(),
                action.getDescription(),
                action.getPriority());

        return _ticketMapper.bugToDto(bug);
    }

    @PutMapping("/bugs/{id}")
    public TicketDetails updateBug(@PathVariable Long id, @RequestBody UpdateBugAction action) {
        log.info("Updating bug with id {}", id);

        var bug = _ticketService.updateBug(
                id,
                action.getTitle(),
                action.getDescription(),
                action.getPriority());

        return _ticketMapper.bugToDto(bug);
    }

    @DeleteMapping("/bugs/{id}")
    public void deleteBugById(@PathVariable Long id) {
        log.info("Deleting bug with id {}", id);

        _ticketService.deleteBug(id);
    }

    @PostMapping("/bugs/{id}/assign/{developerId}")
    public TicketDetails assignBugToDeveloper(@PathVariable("id") Long bugId, @PathVariable Long developerId) {
        log.info("Assigning bug {} to developer {}", bugId, developerId);

        var bug = _ticketService.assignBugToDeveloper(
                bugId,
                developerId);

        return _ticketMapper.bugToDto(bug);
    }

    @GetMapping("/plan")
    public List<List<StoryDetails>> plan() {
        log.info("Planning sprint");

        var planning = _ticketService.getPlanning();

        return _ticketMapper.planningToDto(planning);
    }
}
