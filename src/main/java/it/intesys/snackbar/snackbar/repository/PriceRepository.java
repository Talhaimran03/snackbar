package it.intesys.snackbar.snackbar.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PriceRepository {
    private final Map<String, Double> priceByUserId;

    public PriceRepository() {
        priceByUserId = new HashMap<>();

        priceByUserId.put("Mars", 1.0);
        priceByUserId.put("Twix", 1.0);
        priceByUserId.put("Kinder Delice", 2.0);
    }

    public Double getMoneyBySnackId(String snack) {
        return priceByUserId.get(snack);
    }

    public Double updateSnackPrice(String snack, Double price) {
        priceByUserId.put(snack, price);

        return priceByUserId.get(snack);
    }

    public void addSnackPrice(String snack, Double price) {
        priceByUserId.put(snack, price);
        priceByUserId.get(snack);
    }
}
