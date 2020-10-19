package com.cryptos.cryptoalarms.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Crypto {

    @Id
    @Column(unique = true)
    private String name;

    private Double price;

    private Double changePercent;

    @OneToMany(mappedBy = "crypto")
    private Set<MonitoredCrypto> monitoredCryptos;
}
