package com.lavrentieva.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

//implements UserDetails
@Entity
@Table(name = "persons")
@Getter
@Setter
@ToString
public class Person  {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "person_id")
//    private String id;

    @Id
    @Column(name = "person_id")
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private PersonRole role;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Ware> wares = new LinkedHashSet<>();

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<WareMovementRecord> records = new LinkedHashSet<>();

    public void setWares (final Set<Ware> wares){
        wares.forEach(ware -> ware.setPerson(this));
    }

    public void setRecords (final Set<WareMovementRecord> records){
        records.forEach(record -> record.setPerson(this));
    }



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(role);
//    }
//
//    @Override
//    public String getUsername() {
//        return name;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
