package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ware_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "wares")
@Getter
@Setter
@ToString
public abstract class Ware {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ware_id")
    private String id;


    @Column(name = "ware_name")
    private String name;

    private int amount;

    @Column(name = "deployment_date")
    @Temporal(TemporalType.DATE)
    private Date deploymentDate;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne()
    @JoinColumn(name = "group_id")
    private WareGroup wareGroup;

    @ManyToOne()
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "ware", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<WareMovementRecord> records = new LinkedList<>();

}
