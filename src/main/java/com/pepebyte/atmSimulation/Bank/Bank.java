package com.pepebyte.atmSimulation.Bank;

import com.pepebyte.atmSimulation.Account.Account;

public interface Bank {
    Account authenticate(String cardNumber, String pin);
    void addAccount(Account account);
}
