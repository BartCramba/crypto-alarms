package com.cryptos.cryptoalarms.controller;

import com.cryptos.cryptoalarms.dto.AlarmDto;
import com.cryptos.cryptoalarms.dto.AlarmForm;
import com.cryptos.cryptoalarms.service.AlarmService;
import com.cryptos.cryptoalarms.util.UserUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("alarms")
@AllArgsConstructor
public class AlarmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmController.class);

    private AlarmService alarmService;

    @GetMapping
    public String alarms(Model model, @RequestParam(required = false) String sortBy) {
        if (StringUtils.isEmpty(sortBy))
            sortBy = "monitoredCrypto.crypto.name";

        List<AlarmDto> alarms = alarmService.findAllForUser("user", processSort(sortBy));

        if (alarms != null) {
            List<AlarmDto> activeAlarms = alarms.stream()
                    .filter(AlarmDto::isActive)
                    .collect(Collectors.toList());

            List<AlarmDto> inactiveAlarms = alarms.stream()
                    .filter(a -> !a.isActive())
                    .collect(Collectors.toList());

            model.addAttribute("alarms", activeAlarms);
            model.addAttribute("inactiveAlarms", inactiveAlarms);
        }

        return "alarms";
    }

    public void deleteAlarm(@PathVariable(name ="id") Long id) {
        alarmService.delete(id, UserUtil.getCurrentUsername());
    }

    @PostMapping
    public String saveAlarm(@ModelAttribute @Valid AlarmForm alarmForm, @RequestHeader("Referer") String referer, RedirectAttributes redirectAttributes) throws URISyntaxException {
        try {
            alarmService.save(alarmForm, UserUtil.getCurrentUsername());
            redirectAttributes.addFlashAttribute("alarmSaved", true);
        } catch (Exception e) {
            LOGGER.error("Not found exception raised.", e);
            redirectAttributes.addFlashAttribute("alarmError", true);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        URI refererUri = new URI(referer);
        return String.format("redirect:/%s", refererUri.getPath().substring(1));
    }

    private Sort processSort(@RequestParam String sortBy) {
        Sort.Direction direction;
        if (sortBy.startsWith("-")) {
            direction = Sort.Direction.DESC;
            sortBy = sortBy.substring(1);
        } else if (sortBy.startsWith("+")) {
            direction = Sort.Direction.ASC;
            sortBy = sortBy.substring(1);
        } else {
            direction = Sort.Direction.ASC;
        }

        return new Sort(direction, sortBy);
    }
}
