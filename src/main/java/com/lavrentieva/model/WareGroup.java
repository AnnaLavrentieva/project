package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ware_groups")
@Getter
@Setter
@ToString
public class WareGroup {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "group_id")
//    private String id;

    @Id
    @Column(name = "group_id")
    private String name;

    @OneToMany(mappedBy = "wareGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Ware> wares = new LinkedHashSet<>();

    public void setWares (final Set<Ware> wares){
        wares.forEach(ware -> ware.setWareGroup(this));
    }
}
