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

//implements UserDetails
@Entity
@Table(name = "persons")
@Getter
@Setter
@ToString
public class Person  {

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
