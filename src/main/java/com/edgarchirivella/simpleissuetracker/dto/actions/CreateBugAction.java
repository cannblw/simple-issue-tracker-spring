package com.edgarchirivella.simpleissuetracker.dto.actions;

import com.edgarchirivella.simpleissuetracker.domain.BugPriority;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateBugAction {
    @NotEmpty
    private String title;

    private String description;

    @NotEmpty
    private BugPriority priority;
}
