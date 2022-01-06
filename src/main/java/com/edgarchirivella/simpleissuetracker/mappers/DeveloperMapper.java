package com.edgarchirivella.simpleissuetracker.mappers;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
import dto.details.DeveloperDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {
    Developer toEntity(DeveloperDetails source);
    DeveloperDetails toDto(Developer target);
    List<DeveloperDetails> toDto(List<Developer> target);
}
