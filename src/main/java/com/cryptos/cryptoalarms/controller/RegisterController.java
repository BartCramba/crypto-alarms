package com.cryptos.cryptoalarms.controller;

import com.cryptos.cryptoalarms.controller.validator.PersonValidator;
import com.cryptos.cryptoalarms.domain.Person;
import com.cryptos.cryptoalarms.service.PersonService;
import com.cryptos.cryptoalarms.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
@AllArgsConstructor
public class RegisterController {

    private final PersonService personService;
    private final PersonValidator personValidator;
    private final SecurityService securityService;

    @GetMapping
    public String renderSignUp(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute Person person, BindingResult bindingResult, Model model) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("person", person);
            return "register";
        }

        personService.save(person);
        securityService.autoLogin(person.getUsername(), person.getPassword());

        return "redirect:/cryptos";
    }
}
