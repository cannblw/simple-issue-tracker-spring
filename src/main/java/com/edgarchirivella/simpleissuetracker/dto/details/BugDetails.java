package com.edgarchirivella.simpleissuetracker.dto.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BugDetails extends TicketDetails {
    private String status;

    private String priority;
}
