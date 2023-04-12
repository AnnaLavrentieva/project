package com.lavrentieva.service;

import com.lavrentieva.model.Person;
import com.lavrentieva.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
public class PersonService  {
    private final PersonRepository personRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(final Person person) {
        final String encryptedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encryptedPassword);
        personRepository.save(person);
    }

    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(final String id) {
        return personRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Person not found"));
    }

    public List<String> getAllPeopleNames(){
        return personRepository.getAllId();
    }

    public void updateName(final String id, final String name) {
        Objects.requireNonNull(id, "Empty data");
        personRepository.findById(id)
                .ifPresent(person -> {
                    person.setName(name);
                    personRepository.save(person);
                });
    }

    @Transactional
    public void deleteByName(final String name) {
        Objects.requireNonNull(name, "Empty data");
        personRepository.deleteByNameIgnoreCase(name);
    }
}
