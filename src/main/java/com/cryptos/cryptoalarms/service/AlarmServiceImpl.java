package com.alarms.cryptoalarms.service;

import com.alarms.cryptoalarms.domain.Alarm;
import com.alarms.cryptoalarms.domain.Crypto;
import com.alarms.cryptoalarms.domain.MonitoredCrypto;
import com.alarms.cryptoalarms.domain.Person;
import com.alarms.cryptoalarms.dto.AlarmForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alarms.cryptoalarms.repository.AlarmRepository;
import com.alarms.cryptoalarms.repository.CryptoRepository;
import com.alarms.cryptoalarms.repository.MonitoredCryptoRepository;
import com.alarms.cryptoalarms.repository.PersonRepository;
import com.alarms.cryptoalarms.util.UserUtil;

@Service
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final MonitoredCryptoRepository monitoredCryptoRepository;
    private final AlarmRepository alarmRepository;
    private final CryptoRepository cryptoRepository;
    private final PersonRepository personRepository;

    @Override
    @Transactional
    public void save(AlarmForm alarmForm) {

        String username = UserUtil.getCurrentUsername();

        MonitoredCrypto monitoredCrypto = monitoredCryptoRepository.findByPersonUsernameAndCryptoName(username, alarmForm.getCryptoName());

        if (monitoredCrypto == null) {
            Person person = personRepository.findByUsername(username);
            Crypto crypto = cryptoRepository.findByName(alarmForm.getCryptoName());

            monitoredCrypto = new MonitoredCrypto();
            monitoredCrypto.setPerson(person);
            monitoredCrypto.setCrypto(crypto);

            monitoredCryptoRepository.save(monitoredCrypto);
        }

        Alarm alarm = new Alarm();
        alarm.setMonitoredCrypto(monitoredCrypto);
        alarm.setRule(alarmForm.getRule());
        alarm.setAlarmPrice(calculateTargetPrice(monitoredCrypto.getCrypto().getPrice(), alarmForm.getRule()));
    }

    private Double calculateTargetPrice(Double initialPrice, String rule) {
        boolean add = rule.startsWith("+");
        Double percent = Double.valueOf(rule.substring(1));
        Double percentValueOfInitialPrice = (initialPrice * percent / 100);
        if (add) {
            return initialPrice + percentValueOfInitialPrice;
        } else {
            return initialPrice - percentValueOfInitialPrice;
        }


    }

}
