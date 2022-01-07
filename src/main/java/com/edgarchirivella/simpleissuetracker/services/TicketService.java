package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Story;
import com.edgarchirivella.simpleissuetracker.domain.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    Story createStory(String title, String description, Integer points);

    Story updateStory(Long id, String title, String description, Integer points);
}
