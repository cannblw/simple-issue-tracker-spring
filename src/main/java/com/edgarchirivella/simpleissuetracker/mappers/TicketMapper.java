package com.edgarchirivella.simpleissuetracker.mappers;

import com.edgarchirivella.simpleissuetracker.domain.Bug;
import com.edgarchirivella.simpleissuetracker.domain.Story;
import com.edgarchirivella.simpleissuetracker.domain.Ticket;
import com.edgarchirivella.simpleissuetracker.dto.details.TicketDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TicketMapper {
    @Mappings({
        @Mapping(source = "assignedTo.name", target = "assignedTo")
    })
    TicketDetails storyToDto(Story target);

    @Mappings({
        @Mapping(source = "assignedTo.name", target = "assignedTo")
    })
    TicketDetails bugToDto(Bug target);

    @Mappings({
        @Mapping(source = "assignedTo.name", target = "assignedTo")
    })
    TicketDetails ticketToDto(Ticket target);

    List<TicketDetails> storyToDto(List<Story> target);

    List<TicketDetails> bugToDto(List<Bug> target);

    List<TicketDetails> ticketToDto(List<Ticket> target);
}
