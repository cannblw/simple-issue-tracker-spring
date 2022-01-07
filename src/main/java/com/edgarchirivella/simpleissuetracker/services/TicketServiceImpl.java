package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Ticket;
import com.edgarchirivella.simpleissuetracker.repositories.BugRepository;
import com.edgarchirivella.simpleissuetracker.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements TicketService {
    private final StoryRepository _storyRepository;
    private final BugRepository _bugRepository;

    public TicketServiceImpl(
            StoryRepository storyRepository,
            BugRepository bugRepository) {
        _storyRepository = storyRepository;
        _bugRepository = bugRepository;
    }

    @Override
    public List<Ticket> findAll() {
        var stories = _storyRepository.findAll();
        var bugs = _bugRepository.findAll();

        var tickets = Stream
                .concat(stories.stream(), bugs.stream())
                .collect(Collectors.toList());

        return tickets;
    }
}
