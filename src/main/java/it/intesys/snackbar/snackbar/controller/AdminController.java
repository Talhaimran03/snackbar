package it.intesys.snackbar.snackbar.controller;

import it.intesys.snackbar.snackbar.service.SnackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class AdminController {
    private final SnackService snackService;

    public AdminController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping("/recharge-snack")
    public Boolean rechargeWallet(@RequestParam Map<String, String> params) {
        Map<String, Integer> snacks = params.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Integer.parseInt(entry.getValue())));
        return snackService.rechargeSnacks(snacks);
    }

    @GetMapping("/update-snack-price")
    public Boolean updateSnackPrice(@RequestParam("snack") String snack,
                                    @RequestParam("price") Double price ) {

        return snackService.updateSnackPrice(snack, price);
    }

    @GetMapping("/add-snack")
    public Boolean addSnacks(@RequestParam Map<String, String> params) {
        Map<String, Double> snacks = params.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Double.parseDouble(entry.getValue())));
        return snackService.addSnacks(snacks);
    }
}
