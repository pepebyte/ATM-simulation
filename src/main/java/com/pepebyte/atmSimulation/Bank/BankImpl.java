package com.pepebyte.atmSimulation.Bank;

import com.pepebyte.atmSimulation.Account.Account;

import java.util.ArrayList;
import java.util.List;

public class BankImpl implements Bank {

    private final List <Account> accounts;

    public BankImpl() {
        this.accounts = new ArrayList<>();
    }

    @Override
    public Account authenticate(String cardNumber, String pin) {
        for (Account account : accounts){
            if (account.getCardNumber().equals(cardNumber) && account.checkPin(pin)){
                return account;
            }
        }
        throw new IllegalArgumentException("Wrong input!");
    }

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }
}
