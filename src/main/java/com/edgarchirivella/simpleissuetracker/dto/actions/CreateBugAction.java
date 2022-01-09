package com.edgarchirivella.simpleissuetracker.dto.actions;

import com.edgarchirivella.simpleissuetracker.domain.BugPriority;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateBugAction {
    @NotEmpty
    private String title;

    private String description;

    @NotNull
    private BugPriority priority;
}
