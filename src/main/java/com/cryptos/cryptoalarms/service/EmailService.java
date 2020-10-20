package com.cryptos.cryptoalarms.service;

import com.cryptos.cryptoalarms.dto.PersonWithAlarm;
import org.springframework.util.MultiValueMap;

public interface EmailService {

    void send(MultiValueMap<String, PersonWithAlarm> personWithAlarms);
}
