package com.cryptos.cryptoalarms.repository;

import com.cryptos.cryptoalarms.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByUsername(String username);

    @Query(value = "select p from Person p join p.monitoredCryptos m where m.id in (?1)")
    Set<Person> findAllByMonitoredCryptos(Collection<Long> monitoredCryptoIds);

}
