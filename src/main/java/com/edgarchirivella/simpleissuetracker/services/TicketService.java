package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Bug;
import com.edgarchirivella.simpleissuetracker.domain.BugPriority;
import com.edgarchirivella.simpleissuetracker.domain.Story;
import com.edgarchirivella.simpleissuetracker.domain.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAll();

    Story createStory(String title, String description, Integer points);

    Story updateStory(Long id, String title, String description, Integer points);

    void deleteStory(Long id);

    Bug createBug(String title, String description, BugPriority priority);

    Bug updateBug(Long id, String title, String description, BugPriority priority);

    void deleteBug(Long id);
}
