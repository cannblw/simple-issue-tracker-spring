package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.*;
import com.edgarchirivella.simpleissuetracker.exceptions.EntityNotFoundException;
import com.edgarchirivella.simpleissuetracker.repositories.BugRepository;
import com.edgarchirivella.simpleissuetracker.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements TicketService {
    // These could go in application.properties, but let's leave them here now
    private static final Integer _issueIdLength = 5;
    private static final String _issueIdCharset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String _bugIssueIdPrefix = "BUG-";
    private static final String _storyIssueIdPrefix = "STORY-";

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

    @Override
    public Story createStory(String title, String description, Integer points) {
        var story = Story.builder()
                .issueId(generateIssueId(_storyIssueIdPrefix))
                .title(title)
                .description(description)
                .points(points)
                .status(StoryStatus.NEW)
                .build();

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
                .issueId(generateIssueId(_bugIssueIdPrefix))
                .title(title)
                .description(description)
                .priority(priority)
                .status(BugStatus.NEW)
                .build();

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

    private String generateIssueId(String prefix) {
        var rnd = new SecureRandom();

        var sb = new StringBuilder(prefix);

        for (var i = 0; i < _issueIdLength; i++) {
            sb.append(_issueIdCharset.charAt(rnd.nextInt(_issueIdCharset.length())));
        }

        return sb.toString();
    }
}
