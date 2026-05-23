package session14.ex5.models;

import session14.ex5.exceptions.AccountNotFoundException;
import session14.ex5.exceptions.InsufficientBalanceException;
import session14.ex5.exceptions.InvalidAmountException;

import java.util.List;

public class BankAccount {
    private String accountId;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(String amountInput) throws InvalidAmountException {
        double amount = parseAmount(amountInput);
        balance += amount;
    }

    public void withdraw(String amountInput) throws InvalidAmountException, InsufficientBalanceException {
        double amount = parseAmount(amountInput);
        validateEnoughBalance(amount);
        balance -= amount;
    }

    public void transfer(String amountInput, String destinationAccountId, List<BankAccount> accounts)
            throws InvalidAmountException, InsufficientBalanceException, AccountNotFoundException {
        double amount = parseAmount(amountInput);
        validateEnoughBalance(amount);

        BankAccount destinationAccount = findAccountById(destinationAccountId, accounts);
        balance -= amount;
        destinationAccount.balance += amount;
    }

    public void display() {
        System.out.printf("Tài khoản %s - Số dư: %,.0f đồng%n", accountId, balance);
    }

    private double parseAmount(String amountInput) throws InvalidAmountException {
        try {
            double amount = Double.parseDouble(amountInput.trim());
            if (amount <= 0) {
                throw new InvalidAmountException("Số tiền phải lớn hơn 0");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new InvalidAmountException("Số tiền không phải là số hợp lệ");
        }
    }

    private void validateEnoughBalance(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Số dư tài khoản không đủ");
        }
    }

    private BankAccount findAccountById(String accountId, List<BankAccount> accounts) throws AccountNotFoundException {
        for (BankAccount account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Tài khoản đích không tồn tại");
    }
}
