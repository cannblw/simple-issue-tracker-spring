package com.edgarchirivella.simpleissuetracker.dto.details;

import lombok.*;

import java.util.Date;

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

    private Date createdAt;

    private Date updatedAt;
}
