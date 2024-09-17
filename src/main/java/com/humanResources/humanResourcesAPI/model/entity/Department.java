package com.humanResources.humanResourcesAPI.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "descr")
    private String descr;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Position> positions;
}
