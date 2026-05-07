package session8;

import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private double price;

    static int AUTO_ID = 1;
    private final String WAREHOUSE_CODE = "KHO-01";

    public Product() {
        this.id = AUTO_ID;
        AUTO_ID++;
    }

    public Product(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ten san pham: ");
        this.name = scanner.nextLine();

        do {
            System.out.print("Nhap gia san pham: ");
            this.price = scanner.nextDouble();
            if (this.price < 0) {
                System.out.println("Gia san pham khong duoc am");
            }
        } while (this.price < 0);
    }

    public void print() {
        System.out.println(this);
    }

    public double getPrice() {
        return price;
    }

    public static int getCreatedCount() {
        return AUTO_ID - 1;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", WAREHOUSE_CODE='" + WAREHOUSE_CODE + '\'' +
                '}';
    }
}
