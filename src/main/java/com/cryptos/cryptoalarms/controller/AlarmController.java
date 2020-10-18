package com.alarms.cryptoalarms.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alarms.cryptoalarms.service.AlarmService;

@Controller
@RequestMapping("alarms")
@AllArgsConstructor
public class AlarmController {

    private AlarmService alarmService;

    public String alarms(Model model) {
        model.addAttribute("alarms", alarmService);
        return "alarms";
    }
}
