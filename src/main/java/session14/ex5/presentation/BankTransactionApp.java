package session14.ex5.presentation;

import session14.ex5.exceptions.AccountNotFoundException;
import session14.ex5.exceptions.InsufficientBalanceException;
import session14.ex5.exceptions.InvalidAmountException;
import session14.ex5.models.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankTransactionApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        initializeAccounts();

        System.out.println("===== DANH SÁCH TÀI KHOẢN BAN ĐẦU =====");
        displayAccounts();

        deposit();
        withdraw();
        transfer();

        System.out.println();
        System.out.println("===== DANH SÁCH TÀI KHOẢN SAU GIAO DỊCH =====");
        displayAccounts();

        scanner.close();
    }

    private static void initializeAccounts() {
        accounts.add(new BankAccount("A001", 1_000_000));
        accounts.add(new BankAccount("A002", 750_000));
        accounts.add(new BankAccount("A003", 500_000));
    }

    private static void deposit() {
        System.out.println();
        System.out.println("===== GỬI TIỀN =====");
        BankAccount account = getSourceAccount("Nhập số tài khoản cần gửi tiền: ");
        if (account == null) {
            return;
        }

        try {
            account.deposit(getInput("Nhập số tiền cần gửi: "));
            System.out.println("Gửi tiền thành công.");
            account.display();
        } catch (InvalidAmountException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.println();
        System.out.println("===== RÚT TIỀN =====");
        BankAccount account = getSourceAccount("Nhập số tài khoản cần rút tiền: ");
        if (account == null) {
            return;
        }

        try {
            account.withdraw(getInput("Nhập số tiền cần rút: "));
            System.out.println("Rút tiền thành công.");
            account.display();
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void transfer() {
        System.out.println();
        System.out.println("===== CHUYỂN TIỀN =====");
        BankAccount sourceAccount = getSourceAccount("Nhập số tài khoản nguồn: ");
        if (sourceAccount == null) {
            return;
        }

        String destinationAccountId = getInput("Nhập số tài khoản đích: ");
        try {
            sourceAccount.transfer(getInput("Nhập số tiền cần chuyển: "), destinationAccountId, accounts);
            System.out.println("Chuyển tiền thành công.");
        } catch (InvalidAmountException | InsufficientBalanceException | AccountNotFoundException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static BankAccount getSourceAccount(String message) {
        String accountId = getInput(message);
        BankAccount account = findAccountById(accountId);
        if (account == null) {
            System.out.println("Lỗi: Tài khoản không tồn tại");
        }
        return account;
    }

    private static BankAccount findAccountById(String accountId) {
        for (BankAccount account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    private static String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static void displayAccounts() {
        for (BankAccount account : accounts) {
            account.display();
        }
    }
}
