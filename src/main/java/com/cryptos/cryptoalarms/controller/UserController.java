package com.cryptos.cryptoalarms.controller;

import com.cryptos.cryptoalarms.domain.Person;
import com.cryptos.cryptoalarms.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class UserController {

    private final PersonService personService;

    public void register(@RequestBody Person person) {
        personService.save(person);
    }
}
