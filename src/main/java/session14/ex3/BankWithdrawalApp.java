package session14.ex3;

import java.util.Scanner;

public class BankWithdrawalApp {
    private static final long INITIAL_BALANCE = 1_000_000;
    private static final long MINIMUM_BALANCE = 50_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long balance = INITIAL_BALANCE;

        System.out.println("===== RUT TIEN NGAN HANG =====");
        System.out.printf("So du hien tai: %,d dong%n", balance);
        System.out.print("Nhap so tien muon rut: ");

        try {
            long withdrawAmount = Long.parseLong(scanner.nextLine().trim());

            if (withdrawAmount > balance) {
                System.out.println("Lỗi: Số tiền rút vượt quá số dư!");
            } else if (balance - withdrawAmount < MINIMUM_BALANCE) {
                System.out.println("Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!");
            } else {
                balance -= withdrawAmount;
                System.out.printf("Số tiền đã rút: %,d đồng%n", withdrawAmount);
                System.out.printf("Số dư còn lại trong tài khoản: %,d đồng%n", balance);
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
        } finally {
            scanner.close();
        }
    }
}
