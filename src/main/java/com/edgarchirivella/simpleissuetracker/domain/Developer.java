package com.edgarchirivella.simpleissuetracker.domain;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "developers")
@AllArgsConstructor
public class Developer extends BaseEntity {
    @Column(name = "name")
    private String name;
}
