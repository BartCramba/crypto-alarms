package com.cryptos.cryptoalarms.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoSearchResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String symbol;
}
