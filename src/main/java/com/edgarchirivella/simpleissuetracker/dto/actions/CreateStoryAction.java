package com.edgarchirivella.simpleissuetracker.dto.actions;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateStoryAction {
    @NotEmpty
    private String title;

    private String description;

    private Integer points;
}
