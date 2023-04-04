package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@ToString
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "warehouse_id")
    private String id;

    @Column(name = "warehouse_name")
    private String name;

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Ware> wares = new LinkedHashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<WareMovementRecorder> recorders = new LinkedHashSet<>();

}
