package com.edgarchirivella.simpleissuetracker.dto.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDetails {
    private Long id;

    private String issuedId;

    private String title;

    private String description;

    private Integer points;

    private String status;

    private String priority;

    private String assignedTo;
}
