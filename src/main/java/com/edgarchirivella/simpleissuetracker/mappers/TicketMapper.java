package com.edgarchirivella.simpleissuetracker.mappers;

import com.edgarchirivella.simpleissuetracker.domain.Bug;
import com.edgarchirivella.simpleissuetracker.domain.Story;
import com.edgarchirivella.simpleissuetracker.domain.Ticket;
import com.edgarchirivella.simpleissuetracker.domain.TicketType;
import com.edgarchirivella.simpleissuetracker.dto.details.BugDetails;
import com.edgarchirivella.simpleissuetracker.dto.details.StoryDetails;
import com.edgarchirivella.simpleissuetracker.dto.details.TicketDetails;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TicketMapper {
    @Mapping(source = "assignedTo.name", target = "assignedTo")
    StoryDetails storyToDto(Story target);

    @Mapping(source = "assignedTo.name", target = "assignedTo")
    BugDetails bugToDto(Bug target);

    List<StoryDetails> storyToDto(List<Story> target);

    default TicketDetails ticketToDto(Ticket source) {
        if (source instanceof Story) {
            return storyToDto((Story)source);
        } else if (source instanceof Bug) {
            return bugToDto((Bug)source);
        } else {
            throw new RuntimeException("Cannot");
        }
    }

    @AfterMapping
    default void setType(Ticket ticket, @MappingTarget TicketDetails ticketDetails) {
        if (ticket instanceof Story) {
            ticketDetails.setType(TicketType.STORY.toString());
        } else if (ticket instanceof Bug) {
            ticketDetails.setType(TicketType.BUG.toString());
        }
    }

    List<TicketDetails> ticketToDto(List<Ticket> target);
}
