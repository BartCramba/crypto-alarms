package com.cryptos.cryptoalarms.service;

import com.cryptos.cryptoalarms.domain.Person;

public interface PersonService {

    void save(Person person);

    Person findByUsername(String username);
}
