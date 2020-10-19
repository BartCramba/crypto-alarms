package com.cryptos.cryptoalarms.repository;

import com.cryptos.cryptoalarms.domain.Alarm;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByMonitoredCryptoPersonUsername(String username, Sort sort);
}
