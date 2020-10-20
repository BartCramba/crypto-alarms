package com.cryptos.cryptoalarms.external;

import com.cryptos.cryptoalarms.config.udilia.UdiliaProperties;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class UdiliaGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(UdiliaGateway.class);

//    private final RestTemplate unwrapRootValueRestTemplate;
    private final RestTemplate restTemplate;
    private final UdiliaProperties udiliaProperties;

    public CryptoSearchResponse[] search(String query) {
        URI url = udiliaProperties.getCryptoSearchUrl(query);

        try {
            return restTemplate.getForObject(url, CryptoSearchResponse[].class);
        } catch (RestClientException e) {
            LOGGER.error(String.format("Error calling udilia API: %s: ", url.toString()), e);
            throw e;
        }
    }
}
