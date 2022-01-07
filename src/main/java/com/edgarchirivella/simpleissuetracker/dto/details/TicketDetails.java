package com.edgarchirivella.simpleissuetracker.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDetails {
    private Long id;

    private String type;

    private String issueId;

    private String title;

    private String description;

    private Integer points;

    private String status;

    private String priority;

    private String assignedTo;
}
