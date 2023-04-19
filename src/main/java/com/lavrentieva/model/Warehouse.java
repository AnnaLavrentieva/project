package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@ToString
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    private String name;

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Ware> wares = new LinkedList<>();

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<WareMovementRecord> records = new LinkedList<>();

}
