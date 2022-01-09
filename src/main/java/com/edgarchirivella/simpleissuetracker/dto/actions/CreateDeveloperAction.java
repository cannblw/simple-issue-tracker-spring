package com.edgarchirivella.simpleissuetracker.dto.actions;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateDeveloperAction {
    @NotEmpty
    private String name;
}
