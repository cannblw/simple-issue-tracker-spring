package com.edgarchirivella.simpleissuetracker.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "developers")
public class Developer extends BaseEntity {
    @Column(name = "name")
    private String name;

    // TODO: Rewrite these constructors using Lombok. They don't seem to be generating correctly
    public Developer(String name) {
        this.name = name;
    }

    public Developer() {
    }

}
