package com.cryptos.cryptoalarms.controller;

import com.cryptos.cryptoalarms.dto.MonitorCryptoForm;
import com.cryptos.cryptoalarms.dto.search.CryptoSearchItem;
import com.cryptos.cryptoalarms.external.CryptoSearchResponse;
import com.cryptos.cryptoalarms.external.UdiliaGateway;
import com.cryptos.cryptoalarms.service.CryptoService;
import com.cryptos.cryptoalarms.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CryptoController {

    private final CryptoService cryptoService;
    private final UdiliaGateway udiliaGateway;

    @GetMapping("search")
    @ResponseBody
    public List<CryptoSearchItem> search(@RequestParam(name = "query") String query) {
        CryptoSearchResponse[] cryptoSearchResponse = udiliaGateway.search(query);

        return Arrays.stream(cryptoSearchResponse)
                .map(item -> new CryptoSearchItem(item.getName(), item.getSymbol()))
                .collect(Collectors.toList());
    }


    @RequestMapping(path = "cryptos", method = {RequestMethod.GET, RequestMethod.POST})
    public String cryptos(Model model) {
        model.addAttribute("cryptos", cryptoService.findAllMonitoredCryptosForUser(UserUtil.getCurrentUsername()));
        return "cryptos";
    }

    @PostMapping(path = "cryptos/monitor")
    public String monitor(@ModelAttribute MonitorCryptoForm monitorCryptoForm, @RequestHeader("Referer") String referer, RedirectAttributes redirectAttributes) throws URISyntaxException {
        String currentUsername = UserUtil.getCurrentUsername();

        cryptoService.addToMonitor(monitorCryptoForm, currentUsername);

        redirectAttributes.addFlashAttribute("cryptoSaved", true);
        redirectAttributes.addFlashAttribute("cryptos", cryptoService.findAllMonitoredCryptosForUser(currentUsername));

        URI refererUri = new URI(referer);
        return String.format("redirect:/%s", refererUri.getPath().substring(1));
    }
}
