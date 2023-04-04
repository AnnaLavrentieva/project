package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

    @Entity
    @Getter
    @Setter
    @ToString
    public class OldData {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private String id;

        @Size(min = 5, max = 20)
        private String name;

        private int price;



//    @PrePersist
//    public void prePersist() {
//        ItemValidator.check(this);
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        ItemValidator.check(this);
//    }
    }

