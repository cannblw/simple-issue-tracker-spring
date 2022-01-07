package com.edgarchirivella.simpleissuetracker.controllers;


import com.edgarchirivella.simpleissuetracker.dto.actions.CreateStoryAction;
import com.edgarchirivella.simpleissuetracker.dto.actions.UpdateStoryAction;
import com.edgarchirivella.simpleissuetracker.dto.details.TicketDetails;
import com.edgarchirivella.simpleissuetracker.mappers.TicketMapper;
import com.edgarchirivella.simpleissuetracker.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public TicketDetails createStory(@RequestBody CreateStoryAction action) {
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


}
