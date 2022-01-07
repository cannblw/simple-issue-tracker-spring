package com.edgarchirivella.simpleissuetracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicationError {
    private String message;
    private Integer status;
}
