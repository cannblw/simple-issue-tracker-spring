package com.edgarchirivella.simpleissuetracker.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "bugs")
public class Bug extends Ticket {
    @Column(name = "priority")
    private BugPriority priority;

    @Column(name = "status")
    private BugStatus status;

    @ManyToOne
    private Developer assignedTo;
}
