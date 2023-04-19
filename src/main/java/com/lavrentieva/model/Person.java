package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@ToString
public class Person {

    @Id
    @Column(name = "person_id")
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private PersonRole role;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Ware> wares = new LinkedList<>();

    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<WareMovementRecord> records = new LinkedList<>();
}
