package com.edgarchirivella.simpleissuetracker.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "stories")
public class Story extends Ticket {
    @Column(name = "points")
    private Integer points;

    @Column(name = "status")
    @Setter(AccessLevel.NONE)
    private StoryStatus status;

    public Story(String title, String description, Integer points) {
        this.title = title;
        this.description = description;
        setPoints(points);
    }

    public void setPoints(Integer points) {
        this.points = points;

        if (points == null) {
            this.status = StoryStatus.NEW;
        } else {
            this.status = StoryStatus.ESTIMATED;
        }
    }
}
