package com.lavrentieva.repository;

import com.lavrentieva.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    @Query(value = "SELECT person_id FROM persons", nativeQuery = true)
    public List<String> getAllId();


    public Optional<Person> getByNameIgnoreCase (final String name);

    @Modifying
    public void deleteByNameIgnoreCase (final String name);
}
