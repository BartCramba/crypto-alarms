package com.cryptos.cryptoalarms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"crypto", "person"})
public class MonitoredCrypto {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Crypto crypto;

    @ManyToOne
    private Person person;

    @OneToMany(mappedBy = "monitoredCrypto")
    private Set<Alarm> alarms;
}
