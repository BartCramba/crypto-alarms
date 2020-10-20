package com.cryptos.cryptoalarms.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
