package com.edgarchirivella.simpleissuetracker.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer extends BaseEntity {
    @Column(name = "name")
    private String name;

    public Developer(String name) {
        this.name = name;
    }
}
