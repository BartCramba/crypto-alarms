package com.cryptos.cryptoalarms.service;


import com.cryptos.cryptoalarms.domain.Person;
import com.cryptos.cryptoalarms.repository.PersonRepository;
import com.cryptos.cryptoalarms.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setRoles(new HashSet<>(roleRepository.findAll()));

        personRepository.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }
}
