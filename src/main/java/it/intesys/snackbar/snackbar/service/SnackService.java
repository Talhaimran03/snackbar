package it.intesys.snackbar.snackbar.service;

import it.intesys.snackbar.snackbar.repository.BoughtSnackRepository;
import it.intesys.snackbar.snackbar.repository.PriceRepository;
import it.intesys.snackbar.snackbar.repository.SnackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class SnackService {
    private static final Logger log = LoggerFactory.getLogger(SnackService.class);

    private final SnackRepository snackRepository;
    private final PriceRepository priceRepository;
    private final BoughtSnackRepository boughtSnackRepository;

    public SnackService(SnackRepository snackRepository, PriceRepository priceRepository, BoughtSnackRepository boughtSnackRepository) {
        this.snackRepository = snackRepository;
        this.priceRepository = priceRepository;
        this.boughtSnackRepository = boughtSnackRepository;
    }

    public Boolean snackExists(String snack) {
        return snackRepository.snackExists(snack);
    }

    public Boolean snackAvailable(String snack) {
        return snackRepository.snackAvailable(snack);
    }

    public Double getBalanceBySnackId(String snack) {
        return priceRepository.getMoneyBySnackId(snack);
    }

    public Integer withdrawAvailability(String snack) {
        return snackRepository.withdrawAvailability(snack);
    }

    public Boolean rechargeSnacks(Map<String, Integer> snacks) {
        for (Map.Entry<String, Integer> entry : snacks.entrySet()) {
            String snack = entry.getKey();
            Integer rechargeAmount = entry.getValue();

            if (!snackRepository.snackExists(snack)) {
                throw new IllegalArgumentException("Snack " + snack + " does not exist");
            }

            snackRepository.rechargeSnack(snack, rechargeAmount);
            log.info("Snack {} has been recharged by {} units", snack, rechargeAmount);
        }

        return true;
    }

    public Boolean updateSnackPrice(String snack, Double price) {
        System.out.println(priceRepository.updateSnackPrice(snack, price));
        return true;
    }

    public Map<String, Integer> getTopSnacks() {
        return boughtSnackRepository.getTopSnacks();
    }

    public Boolean addSnacks(Map<String, Double> snacks) {
        for (Map.Entry<String, Double> entry : snacks.entrySet()) {
            String snack = entry.getKey();
            Double price = entry.getValue();

            if (snackRepository.snackExists(snack)) {
                throw new IllegalArgumentException("Snack " + snack + " already exists");
            }

            snackRepository.addSnack(snack, 0);
            priceRepository.addSnackPrice(snack, price);

            log.info("Snack {} with {} price has been added", snack, price);
        }
        return true;
    }
}
