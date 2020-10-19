package com.cryptos.cryptoalarms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "monitoredCrypto")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MonitoredCrypto monitoredCrypto;

    private Double refferencePrice;

    /**
     * Rule for calculating the alarmPrice
     */
    private String rule;

    private BigDecimal alarmPrice;

    private LocalDateTime triggeredAt;

    private boolean active;
}
