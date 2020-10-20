package com.cryptos.cryptoalarms.controller;

import com.cryptos.cryptoalarms.dto.MonitorCryptoForm;
import com.cryptos.cryptoalarms.service.CryptoService;
import com.cryptos.cryptoalarms.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@AllArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;

    public String cryptos(Model model) {
        model.addAttribute("cryptos", cryptoService.findAllMonitoredCryptosForUser(UserUtil.getCurrentUsername()));
        return "cryptos";
    }

    @PostMapping(path = "cryptos/monitor")
    public String monitor(@ModelAttribute MonitorCryptoForm monitorCryptoForm, @RequestHeader("Referer") String referer, RedirectAttributes redirectAttributes) throws URISyntaxException {
        String currentUsername = UserUtil.getCurrentUsername();

        cryptoService.addToMonitor(monitorCryptoForm, currentUsername);

        redirectAttributes.addFlashAttribute("stockSaved", true);
        redirectAttributes.addFlashAttribute("stocks", cryptoService.findAllMonitoredCryptosForUser(currentUsername));

        URI refererUri = new URI(referer);
        return String.format("redirect:/%s", refererUri.getPath().substring(1));
    }
}
