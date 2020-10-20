package com.cryptos.cryptoalarms.external;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UdiliaTest {

    @Autowired
    private UdiliaGateway udiliaGateway;

    @Test
    public void testSearch() {
        String query = "bi";

        CryptoSearchResponse[] response = udiliaGateway.search(query);
        assertThat(response.length, is(greaterThan(1)));
    }
}
