package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ware_groups")
@Getter
@Setter
@ToString
public class WareGroup {
    @Id
    @Column(name = "group_id")
    private String name;

    @OneToMany(mappedBy = "wareGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Ware> wares = new LinkedList<>();
}
