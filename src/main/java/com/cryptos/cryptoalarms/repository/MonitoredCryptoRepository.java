package com.alarms.cryptoalarms.repository;

import com.alarms.cryptoalarms.domain.MonitoredCrypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoredCryptoRepository extends JpaRepository<MonitoredCrypto, Long> {

    MonitoredCrypto findByPersonUsernameAndCryptoName(String username, String cryptoName);
}
