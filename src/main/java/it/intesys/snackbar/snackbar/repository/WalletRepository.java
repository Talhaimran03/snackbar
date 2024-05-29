package it.intesys.snackbar.snackbar.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WalletRepository {
    private final Map<String, Double> walletByUserId;

    public WalletRepository() {
        walletByUserId = new HashMap<>();

        walletByUserId.put("Talha", 5.0);
        walletByUserId.put("Pietro", 1.0);
        walletByUserId.put("Anna", 2.0);
    }

    public Double getMoneyByUserId(String user) {
        return walletByUserId.get(user);
    }

    public Double withdrawMoney(String user, Double value) {
        walletByUserId.put(user, walletByUserId.get(user) - value);
        return walletByUserId.get(user);
    }

    public Double rechargeWallet(String user, Double rechargeAmount) {
        walletByUserId.put(user, walletByUserId.get(user) + rechargeAmount);
        return walletByUserId.get(user);
    }

}
