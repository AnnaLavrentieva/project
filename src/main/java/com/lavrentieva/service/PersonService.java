package com.lavrentieva.service;

import com.lavrentieva.dto.AnalysisDtoItemsByPerson;
import com.lavrentieva.dto.AnalysisDtoItemsByPersonItemize;
import com.lavrentieva.model.Item;
import com.lavrentieva.model.Person;
import com.lavrentieva.model.Warehouse;
import com.lavrentieva.repository.PersonRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


@Service
public class PersonService {
    private final PersonRepository personRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(@NonNull final Person person) {
        final String encryptedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encryptedPassword);
        personRepository.save(person);
    }

    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(@NonNull final String id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person not found"));
    }

    public List<String> getAllPeopleNames() {
        return personRepository.getAllId();
    }

    public void updateName(final String id, @NonNull final String name) {
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

    public Page<AnalysisDtoItemsByPerson> getItemsByPerson(@NonNull int page, @NonNull int size,
                                                           String keyword) {
        final Pageable pageable = PageRequest.of(page - 1, size);
        return personRepository.getAnalysisDtoItemsByPerson(keyword, pageable);
    }

    public Page<AnalysisDtoItemsByPersonItemize> findItemsByPersonId(@NonNull int page, @NonNull int size,
                                                                     String id) {
        final List<Sort.Order> ordersForSorting = setUpSortList();
        final Pageable pageable = PageRequest.of(page - 1, size, Sort.by(ordersForSorting));
        return personRepository.findPersonItemsByPersonId(id, pageable);
    }

    private List<Sort.Order> setUpSortList() {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "warehouseName"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "wareGroupName"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "deploymentDate"));
        return orders;
    }

}
