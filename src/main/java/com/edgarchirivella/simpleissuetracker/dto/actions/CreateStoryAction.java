package com.edgarchirivella.simpleissuetracker.dto.actions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStoryAction {
    private String title;

    private String description;

    private Integer points;
}
