package com.cryptos.cryptoalarms.repository;

import com.cryptos.cryptoalarms.domain.Alarm;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByMonitoredCryptoPersonUsername(String username, Sort sort);

    @Query(value = "SELECT a FROM Alarm a join a.monitoredCrypto mc join mc.person p WHERE p.username = ?1 and a.id = ?2")
    List<Alarm> findAlarmForPerson(String username, Long alarmId);

    List<Alarm> findAllByMonitoredCryptoCryptoName(String cryptoName);
}
