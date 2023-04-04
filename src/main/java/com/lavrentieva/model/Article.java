package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("article")
@Getter
@Setter
@ToString
public class Article extends Ware {

    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Ware> items= new LinkedHashSet<>();

    public Article() {
    }
}
