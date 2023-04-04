package com.lavrentieva.service;

import com.lavrentieva.model.Person;
import com.lavrentieva.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

//implements UserDetailsService
@Service
public class PersonService  {
    private final PersonRepository personRepository;

//    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(final Person person) {
        personRepository.save(person);
        //перевірити навіщо String - може зробити порожнім?
    }
//    public String save(final Person person) {
//        final String encryptedPassword = passwordEncoder.encode(person.getPassword());
//        person.setPassword(encryptedPassword);
//        return "person added to system";
//        //перевірити навіщо String - може зробити порожнім?
//    }

    public Iterable<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(final String id) {
        return personRepository.findById(id).get();
//                .orElseThrow(()-> new NoSuchElementException("Person not found"));
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return personRepository.getByNameIgnoreCase(username)
//                .orElseThrow(()-> new UsernameNotFoundException("Person not found"));
//    }

    //    private static class UserInputException extends RuntimeException {
//        private UserInputException(String message) {
//            super(message);
//        }
//    }

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
