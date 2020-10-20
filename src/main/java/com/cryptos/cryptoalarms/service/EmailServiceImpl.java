package com.cryptos.cryptoalarms.service;

import com.cryptos.cryptoalarms.dto.PersonWithAlarm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void send(MultiValueMap<String, PersonWithAlarm> personWithAlarms) {
        personWithAlarms.forEach((key, alarms) -> {
            LOGGER.debug(String.format("Sending the following alarms to %s:", key));
            alarms.forEach(alarm -> LOGGER.debug(alarm.toString()));

            String alarmsInfo = alarms.stream().map(Object::toString).collect(Collectors.joining("\n"));

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo("bartstocktest@gmail.com");

            msg.setSubject("Crypto Alarm(s)!");
            msg.setText("Hello, Bart! \n The following cryptos have reached the desired prices : \n" + alarmsInfo);

            javaMailSender.send(msg);
        });
    }
}
