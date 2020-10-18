package com.alarms.cryptoalarms.dto;

import lombok.Data;

@Data
public class AlarmDto {

    private Long Id;

    private double referencePrice;

    private String rule;

    private boolean active;

    private Double alarmPrice;

    /**
     * Variance as percentage from the price when the alarm was defined (referencePrice) and the current price of the monitored stock.
     */
    private Double variance;

    private CryptoDto crypto;

    private String triggeredAt;
}
