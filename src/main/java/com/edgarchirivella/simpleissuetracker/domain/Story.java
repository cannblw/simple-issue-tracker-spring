package com.edgarchirivella.simpleissuetracker.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "stories")
public class Story extends Ticket {
    private Integer points;

    @Column(name = "status")
    private StoryStatus status;
}
