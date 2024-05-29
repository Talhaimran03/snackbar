package it.intesys.snackbar.snackbar.service;

import it.intesys.snackbar.snackbar.repository.UserRepository;
import it.intesys.snackbar.snackbar.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(SnackService.class);

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public Boolean userExists(String user) {
        return userRepository.userExists(user);
    }

    public Double getBalanceByUserId(String user) {
        return walletRepository.getMoneyByUserId(user);
    }

    public Double withdrawMoney(String user, Double snackMoney) {
        return walletRepository.withdrawMoney(user, snackMoney);
    }

    public Boolean rechargeWallet(String user, Double rechargeAmount) throws IllegalAccessException {
        if (!userRepository.userExists(user)) {
            throw new IllegalAccessException("User " + user + " does not exists");
        }

        Double updatedWallet = walletRepository.rechargeWallet(user, rechargeAmount);
        log.info("User {}'s wallet has been recharged up to {}", user, updatedWallet);

        return true;
    }

}
