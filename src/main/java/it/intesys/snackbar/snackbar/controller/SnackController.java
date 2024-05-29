package it.intesys.snackbar.snackbar.controller;

import it.intesys.snackbar.snackbar.service.SnackService;
import it.intesys.snackbar.snackbar.service.VendingMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api")
@RestController
public class SnackController {

    private final VendingMachineService vendingMachineService;
    private final SnackService snackService;

    public SnackController(VendingMachineService vendingMachineService, SnackService snackService) {
        this.vendingMachineService = vendingMachineService;
        this.snackService = snackService;
    }

    @GetMapping("/order-snack")
    public Boolean orderSnack(@RequestParam("user") String user,
                              @RequestParam("snack") String snack) throws Exception {

        return vendingMachineService.orderSnack(user, snack);
    }

    @GetMapping("/top-snacks")
    public Map<String, Integer> getTopSnacks() {
        return snackService.getTopSnacks();
    }


}


