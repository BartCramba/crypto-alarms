package com.cryptos.cryptoalarms.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlarmDto {

    private Long Id;

    private double referencePrice;

    private String rule;

    private boolean active;

    private BigDecimal alarmPrice;

    /**
     * Variance as percentage from the price when the alarm was defined (referencePrice) and the current price of the monitored crypto.
     */
    private Double variance;

    private CryptoDto crypto;

    private String triggeredAt;
}
