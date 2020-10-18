package com.alarms.cryptoalarms.repository;

import com.alarms.cryptoalarms.domain.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long> {

//    @Cacheable(cacheNames = "all-cryptos")
    List<Crypto> findAll();

    List<Crypto> findAllByMonitoredCryptosPersonUsername(String username);

//    @CacheEvict(cacheNames = "all-cryptos")
    Crypto save(Crypto crypto);

    Crypto findByName(String name);
}
