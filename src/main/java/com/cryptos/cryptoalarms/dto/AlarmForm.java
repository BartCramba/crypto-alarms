package com.cryptos.cryptoalarms.dto;

import lombok.Data;

@Data
public class AlarmForm {

    private Long id;

    private String cryptoName;

    /**
     * Rule for calculating the alarmPrice
     */
    private String rule;
}
