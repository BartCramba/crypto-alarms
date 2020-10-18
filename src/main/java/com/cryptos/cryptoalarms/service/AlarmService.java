package com.alarms.cryptoalarms.service;

import com.alarms.cryptoalarms.dto.AlarmDto;
import com.alarms.cryptoalarms.dto.AlarmForm;

public interface AlarmService {

    void save(AlarmForm alarmForm);

//    List<AlarmDto> findAllForUser(String username);
}
