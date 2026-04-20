package session3;

import java.util.Scanner;

public class BillCalculation {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ten khach hang: ");
        String customerName = sc.nextLine();

        System.out.print("Nhap ten san pham: ");
        String productName = sc.nextLine();

        System.out.print("Nhap gia san pham: ");
        double price = sc.nextDouble();

        System.out.print("Nhap so luong: ");
        int quantity = sc.nextInt();

        System.out.print("La thanh vien(true/false): ");
        boolean isMember = sc.nextBoolean();

        double amount = price * quantity;

        double discount = 0;
        if (isMember) {
            discount = amount * 0.10;
        }

        double amountWithDiscount = amount - discount;
        double vat = amountWithDiscount * 0.08;

        double totalAmount = amountWithDiscount + vat;

        System.out.println("\n===== Hoa Don =====");
        System.out.println("Khach hang: " + customerName);
        System.out.println("San pham: " + productName);
        System.out.println("Don gia: " + price);
        System.out.println("So luong: " + quantity);
        System.out.println("Thanh tien: " + amount);
        System.out.println("Giam gia: " + discount);
        System.out.println("VAT (8%): " + vat);
        System.out.println("Tong thanh toan: " + totalAmount);
    }
}
