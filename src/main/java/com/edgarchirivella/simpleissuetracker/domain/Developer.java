package com.edgarchirivella.simpleissuetracker.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="developer_id")
    private Set<Story> stories;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="developer_id")
    private Set<Bug> bugs;

    public Developer(String name) {
        this.name = name;
    }
}
