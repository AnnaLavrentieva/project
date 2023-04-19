package com.lavrentieva.service;

import com.lavrentieva.model.SecurityPerson;
import com.lavrentieva.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public JpaUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository
                .findById(username)
                .map(SecurityPerson::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
