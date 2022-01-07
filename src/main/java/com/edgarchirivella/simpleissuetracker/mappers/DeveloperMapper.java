package com.edgarchirivella.simpleissuetracker.mappers;

import com.edgarchirivella.simpleissuetracker.domain.Developer;
import com.edgarchirivella.simpleissuetracker.dto.details.DeveloperDetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeveloperMapper {
    DeveloperDetails toDto(Developer target);
    List<DeveloperDetails> toDto(List<Developer> target);
}
