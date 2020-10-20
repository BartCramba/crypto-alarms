package com.cryptos.cryptoalarms.service;

import com.cryptos.cryptoalarms.domain.Crypto;
import com.cryptos.cryptoalarms.dto.MonitorCryptoForm;

import java.util.List;

public interface CryptoService {

    void save(Crypto crypto);

    List<Crypto> findAllMonitoredCryptosForUser(String username);

    void addToMonitor(MonitorCryptoForm monitorCryptoForm, String username);
}
