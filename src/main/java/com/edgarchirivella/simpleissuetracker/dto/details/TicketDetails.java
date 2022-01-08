package com.edgarchirivella.simpleissuetracker.dto.details;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDetails {
    private Long id;

    private String issueId;

    private String title;

    private String description;

    private String assignedTo;

    private String type;
}
