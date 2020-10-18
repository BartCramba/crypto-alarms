package com.alarms.cryptoalarms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class CryptoAlarmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoAlarmsApplication.class, args);
    }
}
