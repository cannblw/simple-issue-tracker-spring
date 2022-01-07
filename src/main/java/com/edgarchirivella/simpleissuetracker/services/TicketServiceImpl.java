package com.edgarchirivella.simpleissuetracker.services;

import com.edgarchirivella.simpleissuetracker.domain.Story;
import com.edgarchirivella.simpleissuetracker.domain.StoryStatus;
import com.edgarchirivella.simpleissuetracker.domain.Ticket;
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
                .issuedId(generateIssueId(_bugIssueIdPrefix))
                .title(title)
                .description(description)
                .points(points)
                .status(StoryStatus.NEW)
                .build();

        _storyRepository.saveAndFlush(story);

        return story;
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
