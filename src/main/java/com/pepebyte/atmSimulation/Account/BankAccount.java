package com.pepebyte.atmSimulation.Account;

public class BankAccount implements Account {

    private final String cardNumber;
    private final String pin;
    private double balance;

    public BankAccount(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }


    @Override
    public boolean checkPin(String pin) {
        return this.pin.equals(pin);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("Wrong input!");
        }
        this.balance+=amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("Wrong input!");
        }
        if (amount > balance){
            throw new IllegalArgumentException("Insufficient funds!");
        }
        this.balance-=amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getCardNumber() {
        return cardNumber;
    }

}
