package com.edgarchirivella.simpleissuetracker.dto.actions;

import com.edgarchirivella.simpleissuetracker.domain.BugPriority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBugAction {
    private String title;

    private String description;

    private BugPriority priority;
}
