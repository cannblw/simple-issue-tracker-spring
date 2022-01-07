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
    private String issueId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Developer assignedTo;

    @Transient
    private TicketType type;
}
