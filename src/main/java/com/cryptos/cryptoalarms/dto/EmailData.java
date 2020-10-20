package com.cryptos.cryptoalarms.dto;

import lombok.Data;

import java.util.Map;

@Data
public class EmailData {

    private String destinationEmail;

    private Map<String, String> data;
}
