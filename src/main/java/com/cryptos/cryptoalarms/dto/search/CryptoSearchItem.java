package com.cryptos.cryptoalarms.dto.search;

import lombok.Data;

@Data
public class CryptoSearchItem {

    private String name;
    private String symbol;

    public CryptoSearchItem(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
