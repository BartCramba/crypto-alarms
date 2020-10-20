package com.cryptos.cryptoalarms.domain.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

@Getter
public class CryptoUpdatedEvent  extends ApplicationEvent {

    private String cryptoName;

    private BigDecimal price;

    public CryptoUpdatedEvent(Object source, String cryptoName, BigDecimal price) {
        super(source);

        this.cryptoName = cryptoName;
        this.price = price;
    }
}
