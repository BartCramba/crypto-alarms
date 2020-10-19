package com.cryptos.cryptoalarms.service;

import com.cryptos.cryptoalarms.dto.AlarmDto;
import com.cryptos.cryptoalarms.dto.AlarmForm;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AlarmService {

    void save(AlarmForm alarmForm, String username);

    List<AlarmDto> findAllForUser(String username, Sort sort);
}
