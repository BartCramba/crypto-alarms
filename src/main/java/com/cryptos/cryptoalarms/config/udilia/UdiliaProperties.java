package com.cryptos.cryptoalarms.config.udilia;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Min;
import java.net.URI;

@Configuration
@ConfigurationProperties(prefix = "udilia")
@Data
@Validated
public class UdiliaProperties {

    private String apiUrl;
    private String apiAutocomplete;

    private static final String SEARCH_QUERY = "searchQuery";

    @Min(value = 1000, message = "Please provide a value larger than 1 second.")
    private int pollerRate;

    public URI getCryptoSearchUrl(String query) {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(apiUrl)
                .path(apiAutocomplete)
                .queryParam(SEARCH_QUERY, query)
                .build()
                .toUri();
    }
}
