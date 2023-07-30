package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ware_movement_records")
@Getter
@Setter
@ToString
public class WareMovementRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "record_id")
    private String id;

    @Column(name = "record_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    private Movement movement;

    @ManyToOne()
    @JoinColumn(name = "ware_id")
    private Ware ware;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne()
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

}
