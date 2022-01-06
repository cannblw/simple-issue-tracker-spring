package com.edgarchirivella.simpleissuetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "developers")
public class Developer extends BaseEntity {
    @Column(name = "name")
    private String name;
}
