package com.pepebyte.atmSimulation.Account;

public interface Account {
    boolean checkPin(String pin);
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    String getCardNumber();

}
