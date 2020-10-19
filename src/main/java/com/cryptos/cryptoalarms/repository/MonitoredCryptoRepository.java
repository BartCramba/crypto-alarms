package com.cryptos.cryptoalarms.repository;

import com.cryptos.cryptoalarms.domain.MonitoredCrypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoredCryptoRepository extends JpaRepository<MonitoredCrypto, Long> {

    MonitoredCrypto findByPersonUsernameAndCryptoName(String username, String cryptoName);
}
