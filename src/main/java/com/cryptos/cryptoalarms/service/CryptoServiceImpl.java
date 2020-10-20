package com.cryptos.cryptoalarms.service;

import com.cryptos.cryptoalarms.domain.Crypto;
import com.cryptos.cryptoalarms.domain.MonitoredCrypto;
import com.cryptos.cryptoalarms.domain.event.CryptoUpdatedEvent;
import com.cryptos.cryptoalarms.dto.MonitorCryptoForm;
import com.cryptos.cryptoalarms.repository.CryptoRepository;
import com.cryptos.cryptoalarms.repository.MonitoredCryptoRepository;
import com.cryptos.cryptoalarms.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final CryptoRepository cryptoRepository;
    private final PersonRepository personRepository;
    private final MonitoredCryptoRepository monitoredCryptoRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public void save(Crypto crypto) {
        cryptoRepository.save(crypto);

        applicationEventPublisher.publishEvent(new CryptoUpdatedEvent(this, crypto.getName(), new BigDecimal(crypto.getPrice())));
    }

    @Override
    @Transactional
    public List<Crypto> findAllMonitoredCryptosForUser(String username) {
        return cryptoRepository.findAllByMonitoredCryptosPersonUsername(username);
    }

    @Override
    public void addToMonitor(MonitorCryptoForm monitorCryptoForm, String username) {

        Crypto crypto = cryptoRepository.findByName(monitorCryptoForm.getCryptoName());

        if (crypto == null) {
            crypto = new Crypto();

            crypto.setName(monitorCryptoForm.getCryptoName());
            // TODO : set price and change percent with the latest values found

            cryptoRepository.save(crypto);
        }

        MonitoredCrypto monitoredCrypto = monitoredCryptoRepository.findByPersonUsernameAndCryptoName(username, monitorCryptoForm.getCryptoName());

        if (monitoredCrypto != null) {
            // already monitored
            return;
        }

        monitoredCrypto = new MonitoredCrypto();
        monitoredCrypto.setCrypto(crypto);
        monitoredCrypto.setPerson(personRepository.findByUsername(username));
        monitoredCryptoRepository.save(monitoredCrypto);
    }
}
