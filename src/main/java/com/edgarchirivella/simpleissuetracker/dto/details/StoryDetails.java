package com.edgarchirivella.simpleissuetracker.dto.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoryDetails extends TicketDetails {
    private Integer points;

    private String status;
}
