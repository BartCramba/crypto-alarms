package com.cryptos.cryptoalarms.service;

import com.cryptos.cryptoalarms.domain.Alarm;
import com.cryptos.cryptoalarms.domain.Crypto;
import com.cryptos.cryptoalarms.domain.MonitoredCrypto;
import com.cryptos.cryptoalarms.domain.Person;
import com.cryptos.cryptoalarms.dto.AlarmDto;
import com.cryptos.cryptoalarms.dto.AlarmForm;
import com.cryptos.cryptoalarms.repository.AlarmRepository;
import com.cryptos.cryptoalarms.repository.CryptoRepository;
import com.cryptos.cryptoalarms.repository.MonitoredCryptoRepository;
import com.cryptos.cryptoalarms.repository.PersonRepository;
import com.cryptos.cryptoalarms.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final MonitoredCryptoRepository monitoredCryptoRepository;
    private final AlarmRepository alarmRepository;
    private final CryptoRepository cryptoRepository;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public void save(AlarmForm alarmForm, String username) {
        MonitoredCrypto monitoredCrypto = monitoredCryptoRepository.findByPersonUsernameAndCryptoName(username, alarmForm.getCryptoName());

        if (monitoredCrypto == null) {
            Person person = personRepository.findByUsername(username);
            Crypto crypto = cryptoRepository.findByName(alarmForm.getCryptoName());

            monitoredCrypto = new MonitoredCrypto();
            monitoredCrypto.setPerson(person);
            monitoredCrypto.setCrypto(crypto);

            monitoredCryptoRepository.save(monitoredCrypto);
        }

        Alarm alarm;

        if (alarmForm.getId() != null)
            alarm = alarmRepository.getOne(alarmForm.getId());
        else {
            alarm = new Alarm();
            alarm.setTriggeredAt(LocalDateTime.now());
        }
        alarm.setMonitoredCrypto(monitoredCrypto);
        alarm.setRule(alarmForm.getRule());
        alarm.setAlarmPrice(calculateTargetPrice(BigDecimal.valueOf(monitoredCrypto.getCrypto().getPrice()), alarmForm.getRule()));
        alarm.setRefferencePrice(monitoredCrypto.getCrypto().getPrice());
        alarm.setActive(true);

        alarmRepository.save(alarm);
    }

    @Override
    public List<AlarmDto> findAllForUser(String username, Sort sort) {
        List<Alarm> alarms = alarmRepository.findAllByMonitoredCryptoPersonUsername(username, sort);

        return alarms.stream()
                .map(Mapper.toAlarmDto)
                .collect(Collectors.toList());
    }

    private BigDecimal calculateTargetPrice(BigDecimal initialPrice, String rule) {
        boolean add = rule.startsWith("+");
        Double percent = Double.valueOf(rule.substring(1));

        BigDecimal percentValueOfInitialPrice = (initialPrice.multiply(BigDecimal.valueOf(percent))
                .divide(BigDecimal.valueOf(100)));

        if (add) {
            return initialPrice.add(percentValueOfInitialPrice);
        } else {
            return initialPrice.subtract(percentValueOfInitialPrice);
        }
    }
}