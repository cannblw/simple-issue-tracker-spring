package com.edgarchirivella.simpleissuetracker.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bugs")
public class Bug extends Ticket {
    @Column(name = "priority")
    private BugPriority priority;

    @Column(name = "status")
    private BugStatus status;

    public Bug(String title, String description, BugPriority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = BugStatus.NEW;
    }
}
