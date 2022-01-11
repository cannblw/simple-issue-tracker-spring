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
@MappedSuperclass
public abstract class Ticket extends BaseEntity {
    @Column(name = "issue_id")
    protected String issueId;

    @Column(name = "title")
    protected String title;

    @Column(name = "description")
    protected String description;

    @ManyToOne
    protected Developer assignedTo;
}
