package com.pepebyte.atmSimulation;

import com.pepebyte.atmSimulation.Account.Account;
import com.pepebyte.atmSimulation.Account.BankAccount;
import com.pepebyte.atmSimulation.Bank.Bank;
import com.pepebyte.atmSimulation.Bank.BankImpl;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {

        View view = new View(
                System.out::println,
                new Scanner(System.in)::nextLine
        );

        Bank bank = new BankImpl();
        bank.addAccount(new BankAccount("12345678", "1234", 0));

        try {
           String cardNumber = view.inOutValidated(
                    "Enter card number (8 digits):",
                    s -> s.matches("\\d{8}"),
                    "Card number must be 8 digits"
            );
            String pin = view.inOutValidated(
                    "Enter PIN (4 digits):",
                    s -> s.matches("\\d{4}"),
                    "PIN must be 4 digits"
            );

            Account account = bank.authenticate(cardNumber, pin);
            while (true) {

                switch (view.inOut(
                        """
                                1. View the balance
                                2. Withdraw money
                                3. Deposit money
                                0. Exit""",
                        Integer::parseInt, "Enter the correct number")) {
                    case 1:
                        view.out("Your balance " + "$" + account.getBalance());
                        break;
                    case 2:
                        try {
                            double amountW = view.inOut("Enter the withdrawal amount: ",
                                    Double::parseDouble, "Enter the correct number");
                            account.withdraw(amountW);
                            view.out("You have withdrawn " + "$" + amountW + ". New balance " + "$" + account.getBalance());
                        } catch (Exception e){
                            view.out(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            double amountD = view.inOut("Enter the deposit amount: ",
                                    Double::parseDouble, "Enter the correct number");
                            account.deposit(amountD);
                            view.out("You deposited " + "$" + amountD + ". New balance " + "$" + account.getBalance());
                            break;
                        } catch (Exception e){
                            view.out(e.getMessage());
                        }
                        break;
                    default:
                        return;
                }
            }
        } catch (Exception e) {
            view.out(e.getMessage());
        }
    }
}
