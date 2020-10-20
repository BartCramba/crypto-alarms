package com.cryptos.cryptoalarms.dto;

import com.cryptos.cryptoalarms.domain.Alarm;
import com.cryptos.cryptoalarms.domain.Crypto;
import com.cryptos.cryptoalarms.domain.MonitoredCrypto;
import com.cryptos.cryptoalarms.domain.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class PersonWithAlarm {

    private String username;

    private String firstName;

    private String cryptoName;

    private Long alarmId;

    private BigDecimal alarmPrice;

    @Setter
    private BigDecimal currentPrice;

    private BigDecimal initialPrice;

    public PersonWithAlarm() {
    }

    public PersonWithAlarm(MonitoredCrypto monitoredCrypto, Person person, Crypto crypto, Alarm alarm) {
        this.username = person.getUsername();
        this.firstName = person.getFirstName();
        this.cryptoName = crypto.getName();
        this.alarmId = alarm.getId();
        this.initialPrice = new BigDecimal(alarm.getRefferencePrice());
        this.alarmPrice = alarm.getAlarmPrice();
    }
}
