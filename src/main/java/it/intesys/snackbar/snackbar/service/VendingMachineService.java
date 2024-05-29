package it.intesys.snackbar.snackbar.service;

import it.intesys.snackbar.snackbar.repository.BoughtSnackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VendingMachineService {
    
    private static final Logger log = LoggerFactory.getLogger(SnackService.class);

    private final SnackService snackService;
    private final UserService userService;
    private final BoughtSnackRepository boughtSnackRepository;

    public VendingMachineService(SnackService snackService, UserService userService, BoughtSnackRepository boughtSnackRepository) {
        this.snackService = snackService;
        this.userService = userService;
        this.boughtSnackRepository = boughtSnackRepository;
    }

    public Boolean orderSnack(String user, String snack) throws Exception {

        log.info("Request: user → {}, snack → {}", snack, user);
        if (!userService.userExists(user)) {
            throw new IllegalAccessException("User " + user + " does not exists");
        }

        if (!snackService.snackExists(snack)) {
            throw new IllegalAccessException("Snack " + snack + " does not exists");
        }

        if (!snackService.snackAvailable(snack)) {
            log.info("Snack {} is not available", snack);
            throw new IllegalAccessException("Snack " + snack + " is not available");
        }

        Double userMoney = userService.getBalanceByUserId(user);
        Double snackMoney = snackService.getBalanceBySnackId(snack);

        log.info("snack {} costa {} e l'utente {} ha {} euro", snack, snackMoney, user, userMoney);
        if (snackMoney > userMoney ) {
            throw new IllegalAccessException("User " + user + " does non have enough money");
        }

        if (!isSnackFallen()) {
            throw new Exception("Snack does not fall");
        }

        userMoney = userService.withdrawMoney(user, snackMoney);
        Integer snackAvailability = snackService.withdrawAvailability(snack);
        log.info("snack {} ha {} pezzi e l'utente {} ha {} euro", snack, snackAvailability, user, userMoney);

        Integer boughtSnack = boughtSnackRepository.addBoughtSnack(snack);
        log.info("lo snack {} è stato comprato {} volte", snack, boughtSnack);

        return true;
    }

    public Boolean isSnackFallen() {
        Random r = new Random();
        int low = 10;
        int high = 100;
        int random = r.nextInt(high - low) + low;
        return random < 95;
    }
}
