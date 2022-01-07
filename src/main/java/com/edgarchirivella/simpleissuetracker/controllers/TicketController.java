package com.edgarchirivella.simpleissuetracker.controllers;


import com.edgarchirivella.simpleissuetracker.dto.actions.CreateStoryAction;
import com.edgarchirivella.simpleissuetracker.dto.details.TicketDetails;
import com.edgarchirivella.simpleissuetracker.mappers.TicketMapper;
import com.edgarchirivella.simpleissuetracker.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        var tickets = _ticketService.findAll();

        return _ticketMapper.ticketToDto(tickets);
    }

    @PostMapping("/stories")
    public TicketDetails createStory(@RequestBody CreateStoryAction action) {
        var story = _ticketService.createStory(
                action.getTitle(),
                action.getDescription(),
                action.getPoints());

        return _ticketMapper.storyToDto(story);
    }

}
