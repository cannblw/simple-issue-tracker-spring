package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.*;
import com.edgarchirivella.simpleissuetracker.exceptions.EntityNotFoundException;
import com.edgarchirivella.simpleissuetracker.repositories.BugRepository;
import com.edgarchirivella.simpleissuetracker.repositories.DeveloperRepository;
import com.edgarchirivella.simpleissuetracker.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements TicketService {
    // These could go in application.properties, but let's leave them here now
    private static final String _bugIssueIdPrefix = "BUG-";
    private static final String _storyIssueIdPrefix = "STORY-";

    private final StoryRepository _storyRepository;
    private final BugRepository _bugRepository;
    private final DeveloperRepository _developerRepository;

    public TicketServiceImpl(
            StoryRepository storyRepository,
            BugRepository bugRepository,
            DeveloperRepository developerRepository) {
        _storyRepository = storyRepository;
        _bugRepository = bugRepository;
        _developerRepository = developerRepository;
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

    @Override
    public Story createStory(String title, String description, Integer points) {
        var story = Story.builder()
                .title(title)
                .description(description)
                .points(points)
                // Should probably add an endpoint to estimate stories, but for now we'll use the updateStory endpoint for that
                .status(points == null ? StoryStatus.NEW : StoryStatus.ESTIMATED)
                .build();

        _storyRepository.saveAndFlush(story);

        story.setIssueId(_storyIssueIdPrefix + story.getId());
        _storyRepository.saveAndFlush(story);

        return story;
    }

    @Override
    public Story updateStory(Long id, String title, String description, Integer points) {
        var nullableStory = _storyRepository.findById(id);

        if (nullableStory.isEmpty()) {
            throw new EntityNotFoundException();
        }

        var story = nullableStory.get();

        story.setTitle(title);
        story.setDescription(description);
        story.setPoints(points);

        if (points == null) {
            story.setStatus(StoryStatus.NEW);
        } else {
            story.setStatus(StoryStatus.ESTIMATED);
        }

        _storyRepository.saveAndFlush(story);

        return story;
    }

    @Override
    public void deleteStory(Long id) {
        _storyRepository.deleteById(id);
    }

    @Override
    public Bug createBug(String title, String description, BugPriority priority) {
        var bug = Bug.builder()
                .title(title)
                .description(description)
                .priority(priority)
                .status(BugStatus.NEW)
                .build();

        _bugRepository.saveAndFlush(bug);

        bug.setIssueId(_bugIssueIdPrefix + bug.getId());
        _bugRepository.saveAndFlush(bug);

        return bug;
    }

    @Override
    public Bug updateBug(Long id, String title, String description, BugPriority priority) {
        var nullableBug = _bugRepository.findById(id);

        if (nullableBug.isEmpty()) {
            throw new EntityNotFoundException();
        }

        var bug = nullableBug.get();

        bug.setTitle(title);
        bug.setDescription(description);
        bug.setPriority(priority);

        _bugRepository.saveAndFlush(bug);

        return bug;
    }

    @Override
    public void deleteBug(Long id) {
        _bugRepository.deleteById(id);
    }

    @Override
    public Story assignStoryToDeveloper(Long storyId, Long developerId) {
        return (Story) assignTicketToDeveloper(TicketType.STORY, storyId, developerId);
    }

    @Override
    public Bug assignBugToDeveloper(Long bugId, Long developerId) {
        return (Bug) assignTicketToDeveloper(TicketType.BUG, bugId, developerId);
    }

    private Ticket assignTicketToDeveloper(TicketType type, Long ticketId, Long developerId) {
        Optional<? extends Ticket> nullableTicket;
        if (type == TicketType.STORY) {
            nullableTicket = _storyRepository.findById(ticketId);
        } else {
            nullableTicket = _bugRepository.findById(ticketId);
        }

        var nullableDeveloper = _developerRepository.findById(developerId);

        // We could tell the user what entity hasn't been found
        if (nullableTicket.isEmpty() || nullableDeveloper.isEmpty()) {
            throw new EntityNotFoundException();
        }

        var ticket = nullableTicket.get();
        var developer = nullableDeveloper.get();

        ticket.setAssignedTo(developer);

        if (type == TicketType.STORY) {
            _storyRepository.saveAndFlush((Story) ticket);
        } else {
            _bugRepository.save((Bug) ticket);
        }

        return ticket;
    }
}
